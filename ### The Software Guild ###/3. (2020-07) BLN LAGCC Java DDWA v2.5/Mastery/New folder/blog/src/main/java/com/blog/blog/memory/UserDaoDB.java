package com.blog.blog.memory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.blog.blog.models.*;
import com.blog.blog.memory.RoleDaoDB.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

        // for (Blog blog : blogs) {
        //     final String SELECT_TAGS = "SELECT name, Tags.blogID FROM Tags "
        //             + "INNER JOIN UsersBlogs on UsersBlogs.blogID = Tags.blogID " + "WHERE UsersBlogs.userID = ?;";
        //     List<Tag> tags = jdbcTemplate.query(SELECT_TAGS, new TagsMapper(), user.getUserID());
        //     user.setTags(tags);
        // }
        return users;
    }

    @Override
    @Transactional
    public User createUser(User newUser) {
        logger.info(
            newUser.getFirstName() + " | " +
            newUser.getLastName() + " | " +
            newUser.getUsername() + " | " +
            newUser.getPassword()
        );
        final String INSERT_USER = "INSERT INTO Users(firstName, lastName, username, password, enabled) VALUES(?, ?, ?, ?, true);";
        jdbcTemplate.update(INSERT_USER, newUser.getFirstName(), newUser.getLastName(), newUser.getUsername(), newUser.getPassword());
        int newUserID = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        newUser.setUserID(newUserID);
        final String INSERT_USER_ROLES = "INSERT INTO UsersRoles(userID, roleID) VALUES(?, 2);";
        jdbcTemplate.update(INSERT_USER_ROLES, newUser.getUserID());
        Set<Role> roles = getRolesForUser(newUser.getUserID());
        newUser.setRoles(roles);
        return newUser;
    }

    @Override
    public User getUserByUsername(String username) {
        logger.info(username);
        final String SELECT_USER = "SELECT * FROM Users WHERE username = ?;";
        User user = jdbcTemplate.queryForObject(SELECT_USER, new UserMapper(), username);
        user.setRoles(getRolesForUser(user.getUserID()));
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

    @Override
    public void updateUser(User user) {
    //     final String sql = "UPDATE Blogs " + "SET username = ?, date = CURRENT_TIMESTAMP, content = ? "
    //             + "WHERE userID = ?;";
    //     jdbcTemplate.update(sql, user.getusername(), user.getContent(), user.getUserID());

    //     List<Tag> tags = user.getTags();

    //     final String DELETE = "DELETE FROM UsersTags WHERE userID = ?;";
    //     jdbcTemplate.update(DELETE, user.getUserID());

    //     for (Tag tag : tags) {
    //         final String REPLACE_BLOGS_TAGS = "INSERT INTO UsersBlogs(userID, blogID) VALUES(?, ?);";
    //         jdbcTemplate.update(REPLACE_BLOGS_TAGS, user.getUserID(), tag.getblogID());
    //         logger.info("TAG: " + tag.getName());
    //     }
    }

    @Override
    public void deleteUser(User user) {
    //     final String FIRST_DELETE = "DELETE FROM UsersTags WHERE userID = ?;";
    //     jdbcTemplate.update(FIRST_DELETE, user.getUserID());
    //     final String SECOND_DELETE = "DELETE FROM Users WHERE userID = ?;";
    //     jdbcTemplate.update(SECOND_DELETE, user.getUserID());
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
            return user;
        }
    }

}
