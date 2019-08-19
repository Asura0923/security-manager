package sunyu.demo.security.manager.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * security_role table pojo
 *
 * @author SunYu
 */
@TableName("security_role")
public class SecurityRole {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
