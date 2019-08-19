package sunyu.demo.security.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sunyu.demo.security.manager.mapper.SecurityRoleMapper;
import sunyu.demo.security.manager.pojo.SecurityRole;
import sunyu.demo.security.manager.service.SecurityRoleService;

/**
 * role service
 *
 * @author SunYu
 */
@Service
public class SecurityRoleServiceImpl extends ServiceImpl<SecurityRoleMapper, SecurityRole> implements SecurityRoleService {
    @Autowired
    SecurityRoleMapper securityRoleMapper;

    @Override
    public void deleteRoleJoin(Long roleId) {
        securityRoleMapper.deleteRoleResourceJoinByRoleId(roleId);
        securityRoleMapper.deleteUserRoleJoinByRoleId(roleId);
        removeById(roleId);
    }

    @Override
    public void updateRoleResourceJoin(Long roleId, String resourceIds) {
        securityRoleMapper.deleteRoleResourceJoinByRoleId(roleId);
        if (StrUtil.isNotBlank(resourceIds)) {
            for (String resourceId : resourceIds.split(",")) {
                securityRoleMapper.addRoleResourceJoin(roleId, Long.parseLong(resourceId));
            }
        }
    }
}
