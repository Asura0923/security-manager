package sunyu.demo.security.manager.config.tld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * tlds classpath loader
 *
 * @author SunYu
 */
public class ClassPathTldsLoader {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void loadClassPathTlds() {
        freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(classPathTldList);
    }

    final private List<String> classPathTldList;

    public ClassPathTldsLoader(String... classPathTlds) {
        super();
        if (classPathTlds.length == 0) {
            this.classPathTldList = Arrays.asList("/META-INF/security.tld");
        } else {
            this.classPathTldList = Arrays.asList(classPathTlds);
        }
    }
}