package sunyu.demo.security.manager.config.security;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import sunyu.demo.security.manager.pojo.SecurityResource;
import sunyu.demo.security.manager.service.SecurityResourceService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * resources metadata
 *
 * @author SunYu
 */
@Service
public class SecurityFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private Map<String, Collection<ConfigAttribute>> resources = new HashMap<>();

    @Autowired
    SecurityResourceService securityResourceService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (MapUtil.isEmpty(resources)) {
            // init need permission
            securityResourceService
                    .list(Wrappers.<SecurityResource>lambdaQuery().isNotNull(SecurityResource::getUri).eq(SecurityResource::getIntercept, 1))
                    .forEach(securityResource -> {
                        resources.put(securityResource.getUri(), SecurityConfig.createList(SecurityUserDetailsService.ROLEPRE + securityResource.getUri()));
                    })
            ;
        }
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String uri = filterInvocation.getRequest().getRequestURI();
        if (resources.containsKey(uri)) {
            // need permission
            return SecurityConfig.createList(SecurityUserDetailsService.ROLEPRE + uri);
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
