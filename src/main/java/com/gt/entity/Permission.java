package com.gt.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by journal on 2016/6/21.
 */
@Entity
@Table(name = "gt_permission")
public class Permission implements Serializable {

    @Id @GeneratedValue
    private Long id;
    @Column
    private String permission; //权限标识 程序中判断使用,如"user:create"
    @Column
    private String description; //权限描述,UI界面显示使用
    @Column
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "gt_role_permission",
            joinColumns = @JoinColumn(name = "permission_Id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public Permission() {}
    public Permission(String permission, String description, Boolean available) {
        this.permission = permission;
        this.description = description;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
