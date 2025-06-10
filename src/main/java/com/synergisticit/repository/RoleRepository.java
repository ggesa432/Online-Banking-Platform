package com.synergisticit.repository;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/11/2024
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
    Role findTopByOrderByRoleIdDesc();

}
