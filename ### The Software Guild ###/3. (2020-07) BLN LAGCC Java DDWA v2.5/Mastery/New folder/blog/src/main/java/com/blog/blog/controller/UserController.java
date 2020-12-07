package com.blog.blog.controller;

import java.util.*;
import com.blog.blog.memory.*;
import com.blog.blog.models.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    
    @Autowired
    UserDao userDao;
    @Autowired
    BlogDao blogDao;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);



    @GetMapping("myblogs")
    public String myBlogs(Authentication auth, Model model) {
        User user = userDao.getUserByUsername(auth.getName());
        List<Blog> myblogs = new ArrayList<>();
        logger.info("__________________________________");
        for (Blog blog : blogDao.getAllBlogs()) {
            // DISPLAY ADMIN'S OWN BLOGS
            if (blog.getUserID() == user.getUserID()) {
                myblogs.add(blog);
            }
        }
        List<Blog> blogsWaitingForApproval = new ArrayList<>();
        for (Blog blog : myblogs) {
            if (blog.isApproved() == false) {
                blogsWaitingForApproval.add(blog);
            }
        }
        model.addAttribute("myblogs", myblogs);
        model.addAttribute("blogs", blogsWaitingForApproval);
        return "myblogs";
    }

}
