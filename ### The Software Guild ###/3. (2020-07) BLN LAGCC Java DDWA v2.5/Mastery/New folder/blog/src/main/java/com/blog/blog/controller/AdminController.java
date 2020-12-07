package com.blog.blog.controller;

import java.time.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import com.blog.blog.memory.*;
import com.blog.blog.models.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    UserDao userDao;
    @Autowired
    BlogDao blogDao;
    @Autowired
    TagDao tagDao;
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);



    @PostMapping("approveBlog") 
    public String approveBlog(HttpServletRequest request) {
        String approvedBlogs = request.getParameter("approved-blogs");
        String approved[] = approvedBlogs.split(",");
        for (String blogID: approved) {
            Blog blog = blogDao.getBlogByID(Integer.parseInt(blogID));
            blog.setApproved(true);
            blogDao.updateBlog(blog);
        }
        request.getParameter("approved");
        return "redirect:/approveBlogs";
    }
    
}
