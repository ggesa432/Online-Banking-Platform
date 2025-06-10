package com.synergisticit.service;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @author GesangZeren
 * @project OnlineBanking
 * @date 12/16/2024
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private  JdbcTemplate jdbcTemplate;

    private static class RoleRowMapper implements RowMapper<Role> {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();
            role.setRoleId(rs.getLong("roleId"));
            role.setRoleName(rs.getString("role_name"));
            return role;
        }
    }

    @Override
    @Transactional
    public Role createRole(Role role) {
        String sql = "INSERT INTO role (role_name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"roleId"});
            ps.setString(1, role.getRoleName());
            return ps;
        }, keyHolder);

        role.setRoleId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return role;
    }

    @Override
    public List<Role> getAllRoles() {
        String sql = "SELECT * FROM role";
        return jdbcTemplate.query(sql, new RoleRowMapper());
    }

    @Override
    public Role getRoleById(Long id) {
        String sql = "SELECT * FROM role WHERE roleId = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new RoleRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Role not found with id: " + id);
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        String sql = "SELECT * FROM role WHERE role_name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new RoleRowMapper(), roleName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Role updateRole(Long id, Role updatedRole) {
        String sql = "UPDATE role SET role_name = ? WHERE roleId = ?";
        int affected = jdbcTemplate.update(sql, updatedRole.getRoleName(), id);

        if (affected == 0) {
            throw new RuntimeException("Role not found with id: " + id);
        }
        return getRoleById(id);
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        String sql = "DELETE FROM role WHERE roleId = ?";
        int affected = jdbcTemplate.update(sql, id);
        if (affected == 0) {
            throw new RuntimeException("Role not found with id: " + id);
        }
    }

    @Override
    public Long getNextRoleId() {
        String sql = "SELECT MAX(roleId) FROM role";
        Long maxId = jdbcTemplate.queryForObject(sql, Long.class);
        return (maxId == null) ? 1L : maxId + 1;
    }

    @Override
    public List<Role> getAllRolesSorted() {
        String sql = "SELECT * FROM role ORDER BY role_name ASC";
        return jdbcTemplate.query(sql, new RoleRowMapper());
    }



    /*@Autowired
    private RoleRepository roleRepository;


    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById((long) Math.toIntExact(id)).orElseThrow(()->new RuntimeException("Role not found"));
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public Role updateRole(Long id, Role updatedRole) {
        Role existing=getRoleById(id);
        existing.setRoleName(updatedRole.getRoleName());

        return roleRepository.save(existing);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById((long) Math.toIntExact(id));
    }

    @Override
    public Long getNextRoleId() {
        Role maxIdRole = roleRepository.findTopByOrderByRoleIdDesc();
        if (maxIdRole == null) {
            return 1L;
        }
        return maxIdRole.getRoleId() + 1;
    }

    @Override
    public List<Role> getAllRolesSorted() {
        return roleRepository.findAll(Sort.by(Sort.Direction.ASC, "roleName"));
    }*/

}

