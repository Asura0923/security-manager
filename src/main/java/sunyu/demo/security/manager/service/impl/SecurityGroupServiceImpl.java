package sunyu.demo.security.manager.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sunyu.demo.security.manager.mapper.SecurityGroupMapper;
import sunyu.demo.security.manager.pojo.SecurityGroup;
import sunyu.demo.security.manager.service.SecurityGroupService;

import java.util.*;

/**
 * group service
 *
 * @author SunYu
 */
@Service
public class SecurityGroupServiceImpl extends ServiceImpl<SecurityGroupMapper, SecurityGroup> implements SecurityGroupService {
    @Autowired
    SecurityGroupMapper securityGroupMapper;

    @Override
    public void deleteGroupJoin(Long groupId) {
        SecurityGroup group = getById(groupId);
        LambdaUpdateWrapper<SecurityGroup> updateWrapper = Wrappers.<SecurityGroup>lambdaUpdate()
                .eq(SecurityGroup::getPid, groupId)
                .set(SecurityGroup::getPid, group.getPid());
        update(updateWrapper);// update children pid info
        securityGroupMapper.deleteGroupResourceJoinByGroupId(groupId);
        securityGroupMapper.deleteUserGroupJoinByGroupId(groupId);
        removeById(groupId);
    }

    @Override
    public List<SecurityGroup> convertTreeListAddNamePre(List<SecurityGroup> groups, String level2Pre, String levelMorePre) {
        List<SecurityGroup> treeList = convertTreeList(groups);
        String level2 = " └";
        String levelMore = "─";
        if (StrUtil.isNotBlank(level2Pre)) {
            level2 = level2Pre;
        }
        if (StrUtil.isNotBlank(levelMorePre)) {
            levelMore = levelMorePre;
        }
        for (SecurityGroup securityGroup : treeList) {// level1
            if (CollUtil.isNotEmpty(securityGroup.getChildren())) {
                for (SecurityGroup child : securityGroup.getChildren()) {// level2
                    child.setName(level2 + child.getName());
                    if (CollUtil.isNotEmpty(child.getChildren())) {// level more
                        addNamePre(child.getChildren(), level2, levelMore);
                    }
                }
            }
        }
        return treeList;
    }

    private void addNamePre(List<SecurityGroup> groups, String level2Pre, String levelMorePre) {
        for (SecurityGroup group : groups) {
            group.setName(level2Pre + levelMorePre + group.getName());
            if (CollUtil.isNotEmpty(group.getChildren())) {
                levelMorePre += levelMorePre;
                addNamePre(group.getChildren(), level2Pre, levelMorePre);
            }
        }
    }

    /**
     * use group list convert to tree list
     *
     * @param groups
     * @return
     */
    public List<SecurityGroup> convertTreeList(List<SecurityGroup> groups) {
        List<SecurityGroup> groupList = new ArrayList<>();
        Map<Long, SecurityGroup> m = new HashMap<>();
        // construct map data
        for (SecurityGroup securityGroup : groups) {
            m.put(securityGroup.getId(), securityGroup);
        }
        for (SecurityGroup securityGroup : groups) {
            if (securityGroup.getPid() != null) {
                SecurityGroup p = m.get(securityGroup.getPid());
                p.getChildren().add(securityGroup);
                p.getChildren().sort(Comparator.comparingInt(SecurityGroup::getSeq));
            } else {
                groupList.add(securityGroup);
            }
        }
        groupList.sort(Comparator.comparingInt(SecurityGroup::getSeq));
        return groupList;
    }

    @Override
    public List<SecurityGroup> flatTreeList(List<SecurityGroup> groups) {
        List<SecurityGroup> groupList = new ArrayList<>();
        flatTree(groupList, groups);
        return groupList;
    }

    @Override
    public void updateGroupResourceJoin(Long groupId, String resourceIds) {
        securityGroupMapper.deleteGroupResourceJoinByGroupId(groupId);
        if (StrUtil.isNotBlank(resourceIds)) {
            for (String resourceId : resourceIds.split(",")) {
                securityGroupMapper.addGroupResourceJoin(groupId, Long.parseLong(resourceId));
            }
        }
    }

    private void flatTree(List<SecurityGroup> target, List<SecurityGroup> groups) {
        for (SecurityGroup group : groups) {
            target.add(group);
            if (CollUtil.isNotEmpty(group.getChildren())) {
                flatTree(target, group.getChildren());
            }
        }
    }

}
