package com.blog.blog.memory;

import java.util.List;
import com.blog.blog.models.*;

public interface UserDao {
    List<User> getAllUsers();
    User createUser(User newUser);
    User getUserByUsername(String username); 
    User getUserByID(int userID);
    void updateUser(User user);
    void deleteUser(User user);
}
