package sunyu.demo.security.manager.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import sunyu.demo.security.manager.pojo.SecurityResource;
import sunyu.demo.security.manager.pojo.SecurityUser;

import java.util.Collection;
import java.util.List;

/**
 * security info
 *
 * @author SunYu
 */
public class SecurityInfo extends User {
    // user info
    private SecurityUser securityUser;
    // user menu info
    private List<SecurityResource> menu;

    public List<SecurityResource> getMenu() {
        return menu;
    }

    public void setMenu(List<SecurityResource> menu) {
        this.menu = menu;
    }

    public SecurityUser getSecurityUser() {
        return securityUser;
    }

    public void setSecurityUser(SecurityUser securityUser) {
        this.securityUser = securityUser;
    }

    public SecurityInfo(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
