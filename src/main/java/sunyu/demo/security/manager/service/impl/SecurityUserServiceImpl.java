package sunyu.demo.security.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sunyu.demo.security.manager.mapper.SecurityGroupMapper;
import sunyu.demo.security.manager.mapper.SecurityRoleMapper;
import sunyu.demo.security.manager.mapper.SecurityUserMapper;
import sunyu.demo.security.manager.pojo.SecurityUser;
import sunyu.demo.security.manager.service.SecurityUserService;

/**
 * user service
 *
 * @author SunYu
 */
@Service
public class SecurityUserServiceImpl extends ServiceImpl<SecurityUserMapper, SecurityUser> implements SecurityUserService {
    @Autowired
    SecurityUserMapper securityUserMapper;
    @Autowired
    SecurityGroupMapper securityGroupMapper;
    @Autowired
    SecurityRoleMapper securityRoleMapper;

    @Override
    public void deleteUserJoin(Long userId) {
        securityUserMapper.deleteUserResourceJoinByUserId(userId);// delete user resource join info by user id
        securityGroupMapper.deleteUserGroupJoinByUserId(userId);// delete user group join info by user id
        securityRoleMapper.deleteUserRoleJoinByUserId(userId);// delete user role join info by user id
        removeById(userId);//delete user
    }

    @Override
    public void updateUserResourceJoin(Long userId, String resourceIds) {
        securityUserMapper.deleteUserResourceJoinByUserId(userId);
        if (StrUtil.isNotBlank(resourceIds)) {
            for (String resourceId : resourceIds.split(",")) {
                securityUserMapper.addUserResourceJoin(userId, Long.parseLong(resourceId));
            }
        }
    }

    @Override
    public void updateUserGroupJoin(Long userId, String groupIds) {
        securityUserMapper.deleteUserGroupJoinByUserId(userId);
        if (StrUtil.isNotBlank(groupIds)) {
            for (String groupId : groupIds.split(",")) {
                securityUserMapper.addUserGroupJoin(userId, Long.parseLong(groupId));
            }
        }
    }

    @Override
    public void updateUserRoleJoin(Long userId, String roleIds) {
        securityUserMapper.deleteUserRoleJoinByUserId(userId);
        if (StrUtil.isNotBlank(roleIds)) {
            for (String roleId : roleIds.split(",")) {
                securityUserMapper.addUserRoleJoin(userId, Long.parseLong(roleId));
            }
        }
    }
}
