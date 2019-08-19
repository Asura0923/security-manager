package sunyu.demo.security.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sunyu.demo.security.manager.pojo.SecurityUser;

/**
 * user service
 *
 * @author SunYu
 */
public interface SecurityUserService extends IService<SecurityUser> {
    /**
     * delete user resource join info by user id
     * delete user group join info by user id
     * delete user role join info by user id
     * delete user by id
     *
     * @param userId
     */
    void deleteUserJoin(Long userId);

    /**
     * delete user resource join info by user id
     * update user resource join info
     *
     * @param userId
     * @param resourceIds
     */
    void updateUserResourceJoin(Long userId, String resourceIds);

    /**
     * delete user group join info by user id
     * update user group join info
     *
     * @param userId
     * @param groupIds
     */
    void updateUserGroupJoin(Long userId, String groupIds);

    /**
     * delete user role join info by user id
     * update user role join info
     *
     * @param userId
     * @param roleIds
     */
    void updateUserRoleJoin(Long userId, String roleIds);
}
