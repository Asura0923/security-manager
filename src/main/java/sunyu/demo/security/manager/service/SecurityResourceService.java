package sunyu.demo.security.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import sunyu.demo.security.manager.config.security.SecurityInfo;
import sunyu.demo.security.manager.pojo.SecurityResource;
import sunyu.demo.security.manager.pojo.SecurityUser;

import java.util.List;

/**
 * resource service
 *
 * @author SunYu
 */
public interface SecurityResourceService extends IService<SecurityResource> {
    /**
     * delete resource by resourceId and update children pid level plus one
     * delete user resource join info
     * delete group resource join info
     * delete role resource join info
     * delete resource by resourceId
     *
     * @param resourceId
     */
    void deleteResourceJoin(Long resourceId);

    /**
     * refresh user menu tree data
     *
     * @param securityInfo
     */
    void refreshMenu(SecurityInfo securityInfo);

    /**
     * resource list convert tree list
     *
     * @param resources
     * @return
     */
    List<SecurityResource> convertTreeList(List<SecurityResource> resources, Boolean onlyMenu);

    /**
     * resource list convert tree list and add name pre
     *
     * @param resources
     * @param onlyMenu
     * @param level2Pre
     * @param levelMorePre
     * @return
     */
    List<SecurityResource> convertTreeListAddNamePre(List<SecurityResource> resources, Boolean onlyMenu, String level2Pre, String levelMorePre);

    /**
     * flat tree list to resource list
     *
     * @param resources
     * @return
     */
    List<SecurityResource> flatTreeList(List<SecurityResource> resources);

    /**
     * get menu
     *
     * @param onlyMenu only search menu
     * @return
     */
    List<SecurityResource> getResourceTree(Boolean onlyMenu);

    /**
     * get user menu
     * securityUser.email is required
     *
     * @param securityUser
     * @param onlyMenu     only search menu
     * @return
     */
    List<SecurityResource> getResourceTreeByUser(SecurityUser securityUser, Boolean onlyMenu);

}
