package sunyu.demo.security.manager.config.security;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sunyu.demo.security.manager.mapper.SecurityResourceMapper;
import sunyu.demo.security.manager.pojo.SecurityResource;
import sunyu.demo.security.manager.pojo.SecurityUser;
import sunyu.demo.security.manager.service.SecurityResourceService;
import sunyu.demo.security.manager.service.SecurityUserService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * init user premessions
 *
 * @author SunYu
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {
    public static final String ROLEPRE = "ROLE_";

    @Value("${super-admin}")
    String securitySuperAdmin;

    @Value("${super-admin-password}")
    String securitySuperAdminPassword;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SecurityUserService securityUserService;
    @Autowired
    SecurityResourceService securityResourceService;
    @Autowired
    SecurityResourceMapper securityResourceMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SecurityInfo securityInfo = null;
        if (username.equals(securitySuperAdmin)) {//is super admin
            String password = passwordEncoder.encode(securitySuperAdminPassword);
            // add all permission
            securityResourceService
                    .list(Wrappers.<SecurityResource>lambdaQuery().isNotNull(SecurityResource::getUri))
                    .forEach(securityResource -> {
                        authorities.add(new SimpleGrantedAuthority(SecurityUserDetailsService.ROLEPRE + securityResource.getUri()));//add permission to security
                    })
            ;
            securityInfo = new SecurityInfo(username, password, true, true, true, true, authorities);
            SecurityUser user = new SecurityUser();
            user.setName("超级管理员");
            user.setEmail(username);
            securityInfo.setSecurityUser(user);
            securityInfo.setMenu(securityResourceService.getResourceTree(true));
        } else {
            SecurityUser user = securityUserService.getOne(Wrappers.<SecurityUser>lambdaQuery().eq(SecurityUser::getEmail, username));
            if (user == null || user.getEnabled() != 1) {
                // if user is not exists or is not enabled
                securityInfo = new SecurityInfo(username, "", true, true, true, false, authorities);
            } else {
                // update last login time
                user.setLastLogin(DateUtil.now());
                securityUserService.updateById(user);

                // add permission
                for (SecurityResource securityResource : securityResourceMapper.getResourceByUserJoinResource(user)) {
                    authorities.add(new SimpleGrantedAuthority(SecurityUserDetailsService.ROLEPRE + securityResource.getUri()));//add permission to security
                }
                for (SecurityResource securityResource : securityResourceMapper.getResourceByUserJoinRoleJoinResource(user)) {
                    authorities.add(new SimpleGrantedAuthority(SecurityUserDetailsService.ROLEPRE + securityResource.getUri()));//add permission to security
                }
                for (SecurityResource securityResource : securityResourceMapper.getResourceByUserJoinGroupJoinResource(user)) {
                    authorities.add(new SimpleGrantedAuthority(SecurityUserDetailsService.ROLEPRE + securityResource.getUri()));//add permission to security
                }
                securityInfo = new SecurityInfo(username, user.getPassword(), true, true, true, true, authorities);
                securityInfo.setSecurityUser(user);
                securityInfo.setMenu(securityResourceService.getResourceTreeByUser(user, true));
            }
        }
        return securityInfo;
    }

}
