package com.synergisticit.service;

import com.synergisticit.domain.Role;

import java.util.List;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/16/2024
 */
public interface RoleService {
    Role createRole(Role role);
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    Role getRoleByName(String roleName);
    Role updateRole(Long id,Role updatedRole);
    void deleteRole(Long id);
    Long getNextRoleId();

    List<Role> getAllRolesSorted();
}
