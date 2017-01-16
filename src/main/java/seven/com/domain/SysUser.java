package seven.com.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by chenhaijun on 2017/1/3.
 */

@Entity
@Table(name="sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 7411554675989289001L;

    @Id
    @GeneratedValue
    private Long userId;

    private String username;

    private String name;

    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date udateTime;

    private String password;

    private byte salt;

    private Integer state;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="sys_user_role", joinColumns = { @JoinColumn(name = "userId") },
    inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> sysRoles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte getSalt() {
        return salt;
    }

    public void setSalt(byte salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUdateTime() {
        return udateTime;
    }

    public void setUdateTime(Date udateTime) {
        this.udateTime = udateTime;
    }

    public String getCredentialsSalt(){
        return this.username;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    @Override
    public String toString() {
        return "SysUser [id=" + userId + ", username=" + username + ", name=" + name + ", password=" + password
                + ", salt=" + salt + ", state=" + state + "]";
    }
}
