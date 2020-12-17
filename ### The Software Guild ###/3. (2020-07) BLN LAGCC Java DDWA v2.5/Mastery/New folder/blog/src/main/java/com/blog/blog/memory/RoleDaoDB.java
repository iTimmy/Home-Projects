package com.blog.blog.memory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.blog.blog.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RoleDaoDB implements RoleDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Role getRoleByID(int id) {
        try {
            final String SELECT_ROLE_BY_ID = "SELECT * FROM Roles WHERE roleID = ?";
            return jdbc.queryForObject(SELECT_ROLE_BY_ID, new RoleMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Role getRoleByRole(String role) {
        final String SELECT_ROLE = "SELECT * FROM Roles WHERE role = ?";
        return jdbc.queryForObject(SELECT_ROLE, new RoleMapper(), role);
    }

    @Override
    public List<Role> getAllRoles() {
        final String SELECT_ALL_ROLES = "SELECT * FROM Roles";
        return jdbc.query(SELECT_ALL_ROLES, new RoleMapper());
    }

    @Override
    public void deleteRole(int id) {
        final String DELETE_USER_ROLE = "DELETE FROM UsersRoles WHERE roleID = ?";
        final String DELETE_ROLE = "DELETE FROM Roles WHERE roleID = ?";
        jdbc.update(DELETE_USER_ROLE, id);
        jdbc.update(DELETE_ROLE, id);
    }

    @Override
    public void updateRole(Role role) {
        final String UPDATE_ROLE = "UPDATE Roles SET role = ? WHERE roleID = ?";
        jdbc.update(UPDATE_ROLE, role.getRole(), role.getID());
    }

    @Override
    @Transactional
    public Role createRole(Role role) {
        final String INSERT_ROLE = "INSERT INTO Roles(role) VALUES(?)";
        jdbc.update(INSERT_ROLE, role.getRole());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        role.setID(newId);
        return role;
    }

    public static final class RoleMapper implements RowMapper<Role> {

        @Override
        public Role mapRow(ResultSet rs, int i) throws SQLException {
            Role role = new Role();
            role.setID(rs.getInt("roleID"));
            role.setRole(rs.getString("role"));
            return role;
        }
    }
}