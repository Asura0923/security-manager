package sunyu.demo.security.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import sunyu.demo.security.manager.pojo.SecurityGroup;
import sunyu.demo.security.manager.pojo.SecurityResource;
import sunyu.demo.security.manager.pojo.SecurityRole;
import sunyu.demo.security.manager.pojo.SecurityUser;

import java.util.List;

/**
 * resource dao
 *
 * @author SunYu
 */
@Component
public interface SecurityResourceMapper extends BaseMapper<SecurityResource> {

    /**
     * user join resource
     * securityUser.email is required
     *
     * @param securityUser
     * @return
     */
    List<SecurityResource> getResourceByUserJoinResource(@Param("securityUser") SecurityUser securityUser);

    /**
     * user join role join resource
     * securityUser.email is required
     *
     * @param securityUser
     * @return
     */
    List<SecurityResource> getResourceByUserJoinRoleJoinResource(@Param("securityUser") SecurityUser securityUser);

    /**
     * user join group join resource
     * securityUser.email is required
     *
     * @param securityUser
     * @return
     */
    List<SecurityResource> getResourceByUserJoinGroupJoinResource(@Param("securityUser") SecurityUser securityUser);

    /**
     * get resource by role id
     *
     * @param securityRole
     * @return
     */
    List<SecurityResource> getResourceByRoleJoinResource(@Param("securityRole") SecurityRole securityRole);

    /**
     * get resource by group id
     *
     * @param securityGroup
     * @return
     */
    List<SecurityResource> getResourceByGroupJoinResource(@Param("securityGroup") SecurityGroup securityGroup);
}
