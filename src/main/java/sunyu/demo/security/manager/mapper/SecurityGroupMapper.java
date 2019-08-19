package sunyu.demo.security.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import sunyu.demo.security.manager.pojo.SecurityGroup;
import sunyu.demo.security.manager.pojo.SecurityUser;

import java.util.List;

/**
 * group dao
 *
 * @author SunYu
 */
@Component
public interface SecurityGroupMapper extends BaseMapper<SecurityGroup> {

    void deleteGroupResourceJoinByResourceId(@Param("resourceId") Long resourceId);

    void deleteUserGroupJoinByUserId(@Param("userId") Long userId);

    void deleteGroupResourceJoinByGroupId(@Param("groupId") Long groupId);

    void deleteUserGroupJoinByGroupId(@Param("groupId") Long groupId);

    void addGroupResourceJoin(@Param("groupId") Long groupId, @Param("resourceId") Long resourceId);

    List<SecurityGroup> getGroupByUserJoinGroup(@Param("securityUser") SecurityUser securityUser);
}
