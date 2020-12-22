package com.blog.blog.memory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.blog.blog.models.*;
import com.blog.blog.memory.RoleDaoDB.RoleMapper;
import com.blog.blog.memory.ProfileDaoDB.ProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoDB implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static Logger logger = LoggerFactory.getLogger(UserDaoDB.class);



    @Override
    public List<User> getAllUsers() {
        final String sql = "SELECT * FROM Users;";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        for (User user : users) {
            final String SELECT_ROLES = "SELECT role, Roles.roleID FROM Roles "
                    + "INNER JOIN UsersRoles on UsersRoles.roleID = Roles.roleID " + "WHERE UsersRoles.userID = ?;";
            Set<Role> roles = new HashSet(jdbcTemplate.query(SELECT_ROLES, new RoleMapper(), user.getUserID()));
            user.setRoles(roles);
        }
        return users;
    }

    @Override
    @Transactional
    public User createUser(User newUser) {
        final String CREATE_PROFILE = "INSERT INTO Profile(icon, cover, wallpaper) VALUES(?, 'default-cover.jpg', 'default-theme.jpg');";
        jdbcTemplate.update(CREATE_PROFILE, newUser.getProfile().getIcon());
        newUser.getProfile().setProfileID(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));

        final String CREATE_USER = "INSERT INTO Users(firstName, lastName, username, password, enabled, profileID) VALUES(?, ?, ?, ?, true, ?);";
        jdbcTemplate.update(CREATE_USER, newUser.getFirstName(), newUser.getLastName(), newUser.getUsername(), newUser.getPassword(), newUser.getProfile().getProfileID());
        newUser.setUserID(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));

        final String INSERT_USER_ROLES = "INSERT INTO UsersRoles(userID, roleID) VALUES(?, 3);";
        jdbcTemplate.update(INSERT_USER_ROLES, newUser.getUserID());
        Set<Role> roles = getRolesForUser(newUser.getUserID());
        newUser.setRoles(roles);

        return newUser;
    }

    @Override
    public User getUserByUsername(String username) {
        final String SELECT_USER = "SELECT * FROM Users WHERE username = ?;";
        User user = jdbcTemplate.queryForObject(SELECT_USER, new UserMapper(), username);
        user.setRoles(getRolesForUser(user.getUserID()));
        user.setProfile(getProfileForUser(user));
        return user;
    }
    
    @Override
    public User getUserByID(int userID) {
        final String SELECT_USER = "SELECT * FROM Users WHERE userID = ?;";
        User user = jdbcTemplate.queryForObject(SELECT_USER, new UserMapper(), userID);
        user.setRoles(getRolesForUser(user.getUserID()));
        return user;
    }

    private Set<Role> getRolesForUser(int userID) throws DataAccessException {
        final String SELECT_ROLES_FOR_USER = "SELECT * FROM UsersRoles " + "JOIN Roles ON UsersRoles.roleID = Roles.roleID "
                + "WHERE UsersRoles.userID = ?";
        Set<Role> roles = new HashSet(jdbcTemplate.query(SELECT_ROLES_FOR_USER, new RoleMapper(), userID));
        return roles;
    }

    private Profile getProfileForUser(User user) {
        final String SELECT_PROFILE = "SELECT * FROM Profile WHERE profileID = ?;";
        return jdbcTemplate.queryForObject(SELECT_PROFILE, new ProfileMapper(), user.getProfile().getProfileID());
    }

    @Override
    public void updateUser(User user) {
        logger.info(
            user.getRoles() + " | " +
            user.getUsername() + " | " +
            user.getFirstName() + " | " +
            user.getLastName() + " | " +
            user.getPassword() + " | " + 
            user.getProfile()
        );
        final String sql = "UPDATE Users SET " + 
                "profileID = ?, " +
                "username = ?, " +
                "firstName = ?, " +
                "lastName = ?, " +
                "password = ? " +
                "WHERE userID = ?;";
        jdbcTemplate.update(sql, user.getProfile().getProfileID(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getPassword(), user.getUserID());
        
        jdbcTemplate.update("DELETE FROM UsersRoles WHERE userID = ?", user.getUserID());
        for (Role role : user.getRoles()) {
            logger.info(String.valueOf(role.getID()) + ": " + role.getRole());
            final String sql2 = "INSERT INTO UsersRoles(roleID, userID) VALUES(?, ?)";
            jdbcTemplate.update(sql2, role.getID(), user.getUserID());
        }
    }

    @Override
    public void deleteUser(User user) {
        final String FIRST_DELETE = "DELETE FROM Blogs WHERE userID = ?;";
        jdbcTemplate.update(FIRST_DELETE, user.getUserID());
        final String SECOND_DELETE = "DELETE FROM UsersRoles WHERE userID = ?;";
        jdbcTemplate.update(SECOND_DELETE, user.getUserID());
        final String THIRD_DELETE = "DELETE FROM Users WHERE userID = ?;";
        jdbcTemplate.update(THIRD_DELETE, user.getUserID());
    }



    private static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User user = new User();
            user.setUserID(rs.getInt("userID"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEnabled(rs.getBoolean("enabled"));
            user.setProfile(new Profile(
                rs.getInt("profileID"), 
                "default-icon.jpg", 
                "default-cover.jpg", 
                "default-wallpaper.jpg")
            );
            return user;
        }
    }

}
