package com.blog.blog.controller;

import java.time.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import com.blog.blog.memory.*;
import com.blog.blog.models.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    UserDao userDao;
    @Autowired
    BlogDao blogDao;
    @Autowired
    TagDao tagDao;
    @Autowired
    RoleDao roleDao;
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);



    @PostMapping("approveBlog") 
    public String approveBlog(HttpServletRequest request) {
        logger.info("______________________");
        logger.info(request.getParameter("approved-blogs"));
        String approvedBlogs = request.getParameter("approved-blogs");
        String approved[] = approvedBlogs.split(",");
        for (String blogID: approved) {
            Blog blog = blogDao.getBlogByID(Integer.parseInt(blogID));
            blog.setApproved(true);
            blogDao.updateBlog(blog);
        }
        request.getParameter("approved");
        return "redirect:/userManagement";
    }

    @GetMapping("userManagement")
    public String getStaticPage(Model model) {
        boolean hasUserRole = false;
        List<User> users = new ArrayList<>();
        List<Role> roles = roleDao.getAllRoles();
        List<Tag> tags = tagDao.getAllTags();
        for (User user : userDao.getAllUsers()) {
            if (isAdmin(user) == false) {
                users.add(user);
            } else {
                hasUserRole = true;
            }
        }
        if (hasUserRole == true) {
            List<Blog> blogs = new ArrayList<>();
            for (Blog blog : blogDao.getAllBlogs()) {
                // DISPLAYING OTHER USERS' BLOGS
                if (isAdmin(userDao.getUserByID(blog.getUserID())) == false && blog.isApproved() == false) {
                    blogs.add(blog);
                }
            }
            model.addAttribute("blogs", blogs);
        }
        model.addAttribute("tags", tags);
        model.addAttribute("roles", roles);
        model.addAttribute("users", users);
        return "userManagement";
    }

    @PostMapping("userManagement")
    public String postUserManagement(HttpServletRequest request) {
        logger.info("_______________________________________");
        logger.info(request.getParameter("userID"));
        String[] postTypeAndID = request.getParameter("userID").trim().split(":|,");
        for (String s : postTypeAndID) {
            logger.info(s);
        }
        String userID = postTypeAndID[1];
        String newRole = postTypeAndID[2];
        String newUsername = postTypeAndID[3];
        String newFirstName = postTypeAndID[4];
        String newLastName = postTypeAndID[5];
        logger.info(
            userID + " | " +
            newRole + " | " +
            newUsername + " | " +
            newFirstName + " | " +
            newLastName
        );
        User user = userDao.getUserByID(Integer.parseInt(userID));
        if (postTypeAndID[0].equals("edit")) {
            Set<Role> roles = new HashSet<>();
            Role role = roleDao.getRoleByRole(newRole);
            roles.add(role);
            user.setUsername(newUsername);
            user.setFirstName(newFirstName);
            user.setLastName(newLastName);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = !request.getParameter("password").equals("") ? encoder.encode(request.getParameter("password")) : user.getPassword();
            user.setPassword(password);
            user.setRoles(roles);
            userDao.updateUser(user);
        } else if (postTypeAndID[0].equals("delete") && isAdmin(user) == false) {
            logger.info("Deleting " + user.getUsername() + "...");
            userDao.deleteUser(user);
            logger.info(user.getUsername() + "-" + user.getUserID() + ": has been deleted.");
        }
        return "redirect:/userManagement";
    }

    private boolean isAdmin(User user) {
        boolean isAdmin = false;
        for (Role role : user.getRoles()) {
            isAdmin = role.getRole().equals("ROLE_ADMIN") ? true : false;
        }
        return isAdmin;
    }
    
}
