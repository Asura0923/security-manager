package sunyu.demo.security.manager.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;

/**
 * security_resource table pojo
 *
 * @author SunYu
 */
@TableName("security_resource")
public class SecurityResource {
    private Long id;
    private String name;
    private String uri;
    private Long pid;
    private Integer seq;
    private Integer intercept;
    private Integer menu;
    private String icon;

    @TableField(exist = false)
    private List<SecurityResource> children = new ArrayList<>();

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getIntercept() {
        return intercept;
    }

    public void setIntercept(Integer intercept) {
        this.intercept = intercept;
    }

    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<SecurityResource> getChildren() {
        return children;
    }

    public void setChildren(List<SecurityResource> children) {
        this.children = children;
    }
}
