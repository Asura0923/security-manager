package sunyu.demo.security.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import sunyu.demo.security.manager.pojo.SecurityRole;
import sunyu.demo.security.manager.pojo.SecurityUser;

import java.util.List;

/**
 * role dao
 *
 * @author SunYu
 */
@Component
public interface SecurityRoleMapper extends BaseMapper<SecurityRole> {

    void deleteRoleResourceJoinByResourceId(@Param("resourceId") Long resourceId);

    void deleteRoleResourceJoinByRoleId(@Param("roleId") Long roleId);

    void deleteUserRoleJoinByUserId(@Param("userId") Long userId);

    void addRoleResourceJoin(@Param("roleId") Long roleId, @Param("resourceId") Long resourceId);

    void deleteUserRoleJoinByRoleId(@Param("roleId") Long roleId);

    List<SecurityRole> getRoleByUserJoinRole(@Param("securityUser") SecurityUser securityUser);
}
