package sunyu.demo.security.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sunyu.demo.security.manager.pojo.SecurityRole;

/**
 * role service
 *
 * @author SunYu
 */
public interface SecurityRoleService extends IService<SecurityRole> {

    /**
     * delete role resource join info by roleId
     * delete user role join info by roleId
     * delete role by roleId
     *
     * @param roleId
     */
    void deleteRoleJoin(Long roleId);

    /**
     * delete role resource join info by role id
     * update role resource join info
     *
     * @param roleId
     * @param resourceIds
     */
    void updateRoleResourceJoin(Long roleId, String resourceIds);
}
