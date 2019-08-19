package sunyu.demo.security.manager.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * permissions access decision validation
 *
 * @author SunYu
 */
@Service
public class SecurityAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority> userHasPermissions = authentication.getAuthorities();
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String uri = filterInvocation.getRequest().getRequestURI();
        // use uri check permission
        if (userHasPermissions.contains(new SimpleGrantedAuthority(SecurityUserDetailsService.ROLEPRE + uri))) {
            // has permission
            return;
        }
        // no permission
        throw new AccessDeniedException("Permission Denied");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
