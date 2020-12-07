package com.blog.blog.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.blog.blog.memory.*;
import com.blog.blog.models.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {

    @Autowired
    UserDao userDao;
    @Autowired
    BlogDao blogDao;
    @Autowired
    TagDao tagDao;
    private static Logger logger = LoggerFactory.getLogger(BlogController.class);



    @GetMapping("blogs")
    public String getAllBlogs(Model model) {
        List<Blog> blogs = new ArrayList<>();

        for (Blog blog : blogDao.getAllBlogs()) {
            boolean isAdmin = false;
            for (Role role : (userDao.getUserByID(blog.getUserID())).getRoles()) {
                isAdmin = role.getRole().equals("ROLE_ADMIN") ? true : false;
            }
            if (blog.isApproved() == true || isAdmin == true) {
                blogs.add(blog);
            }
        }
        // DISPLAY TAGS FOR EVERY BLOG
        for (Blog b : blogs) {
            String tags = "";
            for (Tag t : b.getTags()) {
                tags += t.getName() + ",";
            }
            logger.info("BLOG " + b.getBlogID() + ": " + b.getTitle() + " | " + tags);
        }

        List<Tag> tags = tagDao.getAllTags();
        List<User> users = userDao.getAllUsers();
        model.addAttribute("blogs", blogs);
        model.addAttribute("tags", tags);
        model.addAttribute("users", users);
        return "blogs";
    }

    @GetMapping("writeBlog")
    public String writeBlog(Model model) {
        List<Tag> tags = tagDao.getAllTags();
        model.addAttribute("tags", tags);
        return "writeBlog";
    }

    @PostMapping("addBlog")
    public String createBlog(Authentication auth, HttpServletRequest request, Model model) {
        User user = userDao.getUserByUsername(auth.getName());
        // IMPORTS TAGS
        List<Tag> tags = tagDao.getAllTags();
        model.addAttribute("tags", tags);
        // CREATE NEW BLOG
        Blog newBlog = new Blog();
        newBlog.setTitle(request.getParameter("title"));
        newBlog.setContent(request.getParameter("content"));
        // GETS LIST OF TAGS FROM HTML
        String[] tagNames = (request.getParameter("name")).split(",");
        // STORED TAGS FROM HTML TO ARRAYLIST
        List<Tag> listTags = new ArrayList();
        for (String name : tagNames) {
            tagDao.createTags(name); // CREATE TAG IF DOESN'T EXIST
            listTags.add(tagDao.getTagByName(name));
        }
        // SETS TAGS AND USER ID TO NEW BLOG
        newBlog.setTags(listTags);
        newBlog.setUserID(user.getUserID());
        // OFFICIALLY CREATES BLOG
        blogDao.createBlog(newBlog);
        return "redirect:/blogs";
    }

    @GetMapping("viewBlog")
    public String getBlog(String title, Model model) {
        Blog blog = blogDao.getBlogByTitle(title);
        model.addAttribute("blog", blog);
        return "viewBlog";
    }

    @GetMapping("editBlog")
    public String editBlog(String title, Model model) {
        List<Tag> tags = tagDao.getAllTags();
        Blog blog = blogDao.getBlogByTitle(title);
        List<Tag> blogstags = blog.getTags();
        model.addAttribute("blog", blog);
        model.addAttribute("tags", tags);
        model.addAttribute("blogstags", blogstags);
        return "editBlog";
    }

    @PostMapping("editBlog")
    public String updateBlog(HttpServletRequest request, Model model) {
        // FETCHES SELECTED BLOG TO BE EDITED
        Blog blog = blogDao.getBlogByID(Integer.parseInt(request.getParameter("blogID").trim()));
        // SETS NEW INFO TO SELECTED BLOG
        blog.setTitle(request.getParameter("title"));
        blog.setContent(request.getParameter("content"));
        blog.setApproved(false);
        // GETS LIST OF TAGS FROM HTML
        String[] tagNames = (request.getParameter("name")).split(",");
        // STORED TAGS FROM HTML TO ARRAYLIST
        List<Tag> listTags = new ArrayList<>();
        for (String name : tagNames) {
            tagDao.createTags(name);
            listTags.add(tagDao.getTagByName(name));
        }
        blog.setTags(listTags);
        blogDao.updateBlog(blog);
        return "redirect:/blogs";
    }

    @GetMapping("deleteBlog")
    public String deleteBlog(HttpServletRequest request, Model model) {
        List<Blog> blogs = blogDao.getAllBlogs();
        model.addAttribute("blogs", blogs);
        blogDao.deleteBlog(blogDao.getBlogByID(Integer.parseInt(request.getParameter("blogID").trim())));
        return "redirect:/blogs";
    }

    @GetMapping("approveBlogs")
    public String approveBlogs(Authentication auth, Model model) {
        User user = userDao.getUserByUsername(auth.getName());
        boolean isAdmin = false;
        List<User> users = userDao.getAllUsers();
        List<Blog> blogs = new ArrayList<>();
        List<Blog> userblogs = new ArrayList<>();
        List<Blog> myblogs = new ArrayList<>();
        logger.info("__________________________________");
        for (Blog blog : blogDao.getAllBlogs()) {
            for (Role role : (userDao.getUserByID(blog.getUserID())).getRoles()) {
                isAdmin = role.getRole().equals("ROLE_ADMIN") ? true : false;
            }
            // DISPLAY ADMIN'S OWN BLOGS
            if (blog.getUserID() == user.getUserID()) {
                userblogs.add(blog);
                myblogs.add(blog);
            }
            // DISPLAYING OTHER USERS' BLOGS
            if (isAdmin == false && blog.isApproved() == false) {blogs.add(blog);}
        }
        model.addAttribute("users", users);
        model.addAttribute("blogs", blogs);
        model.addAttribute("userblogs", userblogs);

        Map<String, User> usersblogs = new HashMap<>();
        model.addAllAttributes(usersblogs);
        model.addAttribute("myblogs", myblogs);
        return "approveBlogs";
    }

}
