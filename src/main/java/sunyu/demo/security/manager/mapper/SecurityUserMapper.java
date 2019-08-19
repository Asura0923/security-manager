package sunyu.demo.security.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import sunyu.demo.security.manager.pojo.SecurityUser;

/**
 * user dao
 *
 * @author SunYu
 */
@Component
public interface SecurityUserMapper extends BaseMapper<SecurityUser> {
    /**
     * delete resource join by user id
     *
     * @param userId
     */
    void deleteUserResourceJoinByUserId(@Param("userId") Long userId);

    /**
     * delete resource join by resource id
     *
     * @param resourceId
     */
    void deleteUserResourceJoinByResourceId(@Param("resourceId") Long resourceId);

    /**
     * add user resource join info to security_user_resource table
     *
     * @param userId
     * @param resourceId
     */
    void addUserResourceJoin(@Param("userId") Long userId, @Param("resourceId") Long resourceId);

    /**
     * delete group join by user id
     *
     * @param userId
     */
    void deleteUserGroupJoinByUserId(@Param("userId") Long userId);

    /**
     * add user group join info to security_user_group table
     *
     * @param userId
     * @param groupId
     */
    void addUserGroupJoin(@Param("userId") Long userId, @Param("groupId") Long groupId);

    /**
     * delete role join by user id
     *
     * @param userId
     */
    void deleteUserRoleJoinByUserId(@Param("userId") Long userId);

    /**
     * add user role join info to security_user_role table
     *
     * @param userId
     * @param roleId
     */
    void addUserRoleJoin(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
