package com.fighting.store.user.entity;

public class Role {
    private Integer id;

    private String roleName;

    private String roleKey;

    public Role(Integer id, String roleName, String roleKey) {
        this.id = id;
        this.roleName = roleName;
        this.roleKey = roleKey;
    }

    public Role() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey == null ? null : roleKey.trim();
    }
}