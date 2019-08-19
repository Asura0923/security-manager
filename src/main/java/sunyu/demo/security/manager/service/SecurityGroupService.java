package sunyu.demo.security.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sunyu.demo.security.manager.pojo.SecurityGroup;

import java.util.List;

/**
 * group service
 *
 * @author SunYu
 */
public interface SecurityGroupService extends IService<SecurityGroup> {

    /**
     * delete group by id and update children pid level plus one
     * delete group resource join info by groupId
     * delete user group join info by groupId
     * delete group by groupId
     *
     * @param groupId
     */
    void deleteGroupJoin(Long groupId);

    /**
     * group list convert tree list and add name pre
     *
     * @param groups
     * @param level2Pre
     * @param levelMorePre
     * @return
     */
    List<SecurityGroup> convertTreeListAddNamePre(List<SecurityGroup> groups, String level2Pre, String levelMorePre);

    /**
     * flat tree list to resource list
     *
     * @param groups
     * @return
     */
    List<SecurityGroup> flatTreeList(List<SecurityGroup> groups);

    /**
     * delete group resource join info by group id
     * update group resource join info
     *
     * @param groupId
     * @param resourceIds
     */
    void updateGroupResourceJoin(Long groupId, String resourceIds);
}
