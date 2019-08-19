package sunyu.demo.security.manager.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;

/**
 * security_group table pojo
 *
 * @author SunYu
 */
@TableName("security_group")
public class SecurityGroup {
    private Long id;
    private String name;
    private Long pid;
    private Integer seq;

    @TableField(exist = false)
    private List<SecurityGroup> children = new ArrayList<>();

    public List<SecurityGroup> getChildren() {
        return children;
    }

    public void setChildren(List<SecurityGroup> children) {
        this.children = children;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
