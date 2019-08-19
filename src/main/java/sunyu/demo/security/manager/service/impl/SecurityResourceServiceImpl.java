package sunyu.demo.security.manager.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sunyu.demo.security.manager.config.security.SecurityInfo;
import sunyu.demo.security.manager.mapper.SecurityGroupMapper;
import sunyu.demo.security.manager.mapper.SecurityResourceMapper;
import sunyu.demo.security.manager.mapper.SecurityRoleMapper;
import sunyu.demo.security.manager.mapper.SecurityUserMapper;
import sunyu.demo.security.manager.pojo.SecurityResource;
import sunyu.demo.security.manager.pojo.SecurityUser;
import sunyu.demo.security.manager.service.SecurityResourceService;

import java.util.*;

/**
 * resource service
 *
 * @author SunYu
 */
@Service
public class SecurityResourceServiceImpl extends ServiceImpl<SecurityResourceMapper, SecurityResource> implements SecurityResourceService {

    @Autowired
    SecurityResourceMapper securityResourceMapper;
    @Autowired
    SecurityUserMapper securityUserMapper;
    @Autowired
    SecurityGroupMapper securityGroupMapper;
    @Autowired
    SecurityRoleMapper securityRoleMapper;

    @Value("${super-admin}")
    String securitySuperAdmin;

    @Override
    public List<SecurityResource> getResourceTree(Boolean onlyMenu) {
        return convertTreeList(list(), onlyMenu);
    }

    @Override
    public void deleteResourceJoin(Long resourceId) {
        SecurityResource resource = getById(resourceId);
        LambdaUpdateWrapper<SecurityResource> updateWrapper = Wrappers.<SecurityResource>lambdaUpdate()
                .eq(SecurityResource::getPid, resourceId)
                .set(SecurityResource::getPid, resource.getPid());
        update(updateWrapper);// update children pid info
        securityUserMapper.deleteUserResourceJoinByResourceId(resourceId);// delete user resource join info
        securityGroupMapper.deleteGroupResourceJoinByResourceId(resourceId);// delete group resource join info
        securityRoleMapper.deleteRoleResourceJoinByResourceId(resourceId);// delete role resource join info
        removeById(resourceId);// delete resource
    }

    @Override
    public void refreshMenu(SecurityInfo securityInfo) {
        if (securityInfo.getUsername().equals(securitySuperAdmin)) {
            securityInfo.setMenu(getResourceTree(true));
        } else {
            securityInfo.setMenu(getResourceTreeByUser(securityInfo.getSecurityUser(), true));
        }
    }

    /**
     * use resource list convert to tree list
     *
     * @param resources
     * @return
     */
    public List<SecurityResource> convertTreeList(List<SecurityResource> resources, Boolean onlyMenu) {
        if (onlyMenu) {
            Iterator<SecurityResource> iterator = resources.iterator();
            while (iterator.hasNext()) {
                SecurityResource resource = iterator.next();
                if (resource.getMenu() != 1) {
                    iterator.remove();
                }
            }
        }
        List<SecurityResource> resourceList = new ArrayList<>();
        Map<Long, SecurityResource> m = new HashMap<>();
        // construct map data
        for (SecurityResource securityResource : resources) {
            m.put(securityResource.getId(), securityResource);
        }
        for (SecurityResource securityResource : resources) {
            if (securityResource.getPid() != null) {
                SecurityResource p = m.get(securityResource.getPid());
                p.getChildren().add(securityResource);
                p.getChildren().sort(Comparator.comparingInt(SecurityResource::getSeq));
            } else {
                resourceList.add(securityResource);
            }
        }
        resourceList.sort(Comparator.comparingInt(SecurityResource::getSeq));
        return resourceList;
    }

    @Override
    public List<SecurityResource> convertTreeListAddNamePre(List<SecurityResource> resources, Boolean onlyMenu, String level2Pre, String levelMorePre) {
        List<SecurityResource> treeList = convertTreeList(resources, onlyMenu);
        String level2 = " └";
        String levelMore = "─";
        if (StrUtil.isNotBlank(level2Pre)) {
            level2 = level2Pre;
        }
        if (StrUtil.isNotBlank(levelMorePre)) {
            levelMore = levelMorePre;
        }
        for (SecurityResource securityResource : treeList) {// level1
            if (CollUtil.isNotEmpty(securityResource.getChildren())) {
                for (SecurityResource child : securityResource.getChildren()) {// level2
                    child.setName(level2 + child.getName());
                    if (CollUtil.isNotEmpty(child.getChildren())) {// level more
                        addNamePre(child.getChildren(), level2, levelMore);
                    }
                }
            }
        }
        return treeList;
    }

    private void addNamePre(List<SecurityResource> resources, String level2Pre, String levelMorePre) {
        for (SecurityResource resource : resources) {
            resource.setName(level2Pre + levelMorePre + resource.getName());
            if (CollUtil.isNotEmpty(resource.getChildren())) {
                levelMorePre += levelMorePre;
                addNamePre(resource.getChildren(), level2Pre, levelMorePre);
            }
        }
    }

    @Override
    public List<SecurityResource> flatTreeList(List<SecurityResource> resources) {
        List<SecurityResource> resourceList = new ArrayList<>();
        flatTree(resourceList, resources);
        return resourceList;
    }

    private void flatTree(List<SecurityResource> target, List<SecurityResource> resources) {
        for (SecurityResource resource : resources) {
            target.add(resource);
            if (CollUtil.isNotEmpty(resource.getChildren())) {
                flatTree(target, resource.getChildren());
            }
        }
    }

    @Override
    public List<SecurityResource> getResourceTreeByUser(SecurityUser securityUser, Boolean onlyMenu) {
        List<SecurityResource> resources = new ArrayList<>();
        if (securitySuperAdmin.equals(securityUser.getEmail())) {
            // all resource tree
            resources = list();
        } else {
            // user resource tree
            resources.addAll(securityResourceMapper.getResourceByUserJoinResource(securityUser));

            // user role resource tree
            resources.addAll(securityResourceMapper.getResourceByUserJoinRoleJoinResource(securityUser));

            // user group resource tree
            resources.addAll(securityResourceMapper.getResourceByUserJoinGroupJoinResource(securityUser));
        }
        return convertTreeList(resources, onlyMenu);
    }

}
