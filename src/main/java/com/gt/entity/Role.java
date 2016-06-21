package com.gt.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by journal on 2016/6/21.
 */
@Entity
@Table(name = "gt_role")
public class Role implements Serializable {

    @Id @GeneratedValue
    private Long id;
    @Column
    private String role; //角色标识 程序中判断使用,如"admin"
    @Column
    private String description; //角色描述,UI界面显示使用
    @Column
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户

    @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    private Set<User> users;
    @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    private Set<Permission> permissions;


    public Role() {
    }

    public Role(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
