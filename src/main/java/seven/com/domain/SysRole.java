package seven.com.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by chenhaijun on 2017/1/12.
 */
@Entity
@Table(name ="sys_role")
public class SysRole implements Serializable{

    private static final long serialVersionUID = 3454767566777822482L;

    @Id
    @GeneratedValue
    private Long roleId;

    private String role;

    private String description;

    private Boolean available = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="sys_role_permission",joinColumns = {@JoinColumn(name="roleId")},
            inverseJoinColumns ={@JoinColumn(name = "permissionId") })
    private List<SysPermission> sysPermissions;

    @ManyToMany
    @JoinTable(name="sys_user_role", joinColumns = { @JoinColumn(name = "roleId") },
            inverseJoinColumns ={@JoinColumn(name = "userId") })
    private List<SysUser> sysUsers;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysPermission> getSysPermissions() {
        return sysPermissions;
    }

    public void setSysPermissions(List<SysPermission> sysPermissions) {
        this.sysPermissions = sysPermissions;
    }


    public List<SysUser> getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(List<SysUser> sysUsers) {
        this.sysUsers = sysUsers;
    }

}
