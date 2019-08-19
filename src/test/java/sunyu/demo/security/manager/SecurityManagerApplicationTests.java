package sunyu.demo.security.manager;

import cn.hutool.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sunyu.demo.security.manager.mapper.SecurityResourceMapper;
import sunyu.demo.security.manager.pojo.SecurityResource;
import sunyu.demo.security.manager.pojo.SecurityUser;
import sunyu.demo.security.manager.service.SecurityResourceService;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityManagerApplicationTests {
    @Autowired
    SecurityResourceService securityResourceService;
    @Autowired
    SecurityResourceMapper securityResourceMapper;

    @Test
    public void t() {
        SecurityUser user = new SecurityUser();
        user.setEmail("sunyu@qq.com");
        for (SecurityResource securityResource : securityResourceMapper.getResourceByUserJoinResource(user)) {
            System.out.println(JSONUtil.toJsonStr(securityResource));
        }
    }

    @Test
    public void t2() {
        List<SecurityResource> resourceList = new ArrayList<>();
        List<SecurityResource> securityResourceList = securityResourceService.list();
        Map<Long, SecurityResource> m = new HashMap<>();
        for (SecurityResource securityResource : securityResourceList) {
            m.put(securityResource.getId(), securityResource);
        }
        for (SecurityResource securityResource : securityResourceList) {
            if (securityResource.getPid() != null) {
                SecurityResource p = m.get(securityResource.getPid());
                p.getChildren().add(securityResource);
                p.getChildren().sort(Comparator.comparingInt(SecurityResource::getSeq));
            } else {
                resourceList.add(securityResource);
            }
        }
        resourceList.sort(Comparator.comparingInt(SecurityResource::getSeq));
        System.out.println(JSONUtil.toJsonPrettyStr(resourceList));
    }

    @Test
    public void t3() {
        System.out.println(JSONUtil.toJsonPrettyStr(securityResourceService.getResourceTree(false)));
    }

    @Test
    public void t4() {
        SecurityUser securityUser = new SecurityUser();
        securityUser.setEmail("89333367@qq.com");
        System.out.println(JSONUtil.toJsonPrettyStr(securityResourceService.getResourceTreeByUser(securityUser, false)));
    }

    @Test
    public void t5() {
        SecurityUser securityUser = new SecurityUser();
        securityUser.setEmail("sunyu@qq.com");
        System.out.println(JSONUtil.toJsonPrettyStr(securityResourceService.getResourceTreeByUser(securityUser, false)));
    }

    @Test
    public void t6() {
        SecurityUser securityUser = new SecurityUser();
        securityUser.setEmail("know@qq.com");
        System.out.println(JSONUtil.toJsonPrettyStr(securityResourceService.getResourceTreeByUser(securityUser, false)));
    }

}
