package sunyu.demo.security.manager.config.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sunyu.demo.security.manager.config.tld.ClassPathTldsLoader;

/**
 * freemarker load tlds
 * <#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>
 * <@security.authorize access="hasRole('admin')">to do</@security.authorize>
 * <@security.authentication property="principal.username"/>
 *
 * @author SunYu
 */
@Configuration
public class SecurityTagConfig {
    @Bean
    @ConditionalOnMissingBean(ClassPathTldsLoader.class)
    public ClassPathTldsLoader classPathTldsLoader() {
        return new ClassPathTldsLoader();
    }
}
