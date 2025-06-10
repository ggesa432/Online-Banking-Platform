package com.synergisticit.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GesangZeren
 * @project Day56-14Nov2024-Assignment9-SpringBoot-MVC
 * @date 11/14/2024
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @NotEmpty
    @Column(name = "role_name",unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public @NotEmpty String getRoleName() {
        return roleName;
    }

    public void setRoleName(@NotEmpty String roleName) {
        this.roleName = roleName;
    }
}
