package sunyu.demo.security.manager.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * security config
 *
 * @author SunYu
 */
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {
    @Autowired
    SecurityUserDetailsService securityUserDetailsService;
    @Autowired
    SecurityAccessDecisionManager securityAccessDecisionManager;
    @Autowired
    SecurityFilterInvocationSecurityMetadataSource securityFilterInvocationSecurityMetadataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // csrf config
                .csrf().disable()

                // user permission init
                .userDetailsService(securityUserDetailsService)

                // all request need authenticated
                .authorizeRequests()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    public <O extends FilterSecurityInterceptor> O postProcess(
                            O filterSecurityInterceptor) {
                        // determine if permission authentication is required
                        filterSecurityInterceptor.setSecurityMetadataSource(securityFilterInvocationSecurityMetadataSource);
                        // authority authentication
                        filterSecurityInterceptor.setAccessDecisionManager(securityAccessDecisionManager);
                        return filterSecurityInterceptor;
                    }
                })

                .and()

                // login config
                .formLogin()
                .loginPage("/security/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()

                .and()

                // logout config
                .logout()
                .permitAll()
        ;
    }

}
