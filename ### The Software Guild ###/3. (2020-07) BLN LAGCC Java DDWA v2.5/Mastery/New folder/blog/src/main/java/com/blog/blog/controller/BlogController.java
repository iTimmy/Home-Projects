package com.blog.blog.controller;

import java.security.Principal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import com.blog.blog.memory.*;
import com.blog.blog.models.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    Set<ConstraintViolation<Blog>> violations = new HashSet<>();



    @GetMapping("blogs")
    public String getAllBlogs(Model model) {
        List<Blog> blogs = new ArrayList<>();
        for (Blog blog : blogDao.getAllBlogs()) {
            boolean isAdmin = false;
            for (Role role : (userDao.getUserByID(blog.getUserID())).getRoles()) {
                isAdmin = role.getRole().equals("ROLE_ADMIN") ? true : false;
            }
            if (blog.isApproved() == true || isAdmin == true) {
                if (blog.getPostDate() == null || blog.getExpirationDate() == null) {
                    blogs.add(blog);
                } else if (blog.getPostDate() != null || blog.getExpirationDate() != null) {
                    if (LocalDate.now().isAfter(blog.getPostDate())
                            && LocalDate.now().isBefore(blog.getExpirationDate())) {
                        blogs.add(blog);
                    }
                }
            }
        }
        // DISPLAY TAGS FOR EVERY BLOG
        for (Blog b : blogs) {
            String tags = "";
            for (Tag t : b.getTags()) {
                tags += t.getName() + ",";
            }
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
    public String createBlog(Authentication auth, 
    Principal principal, HttpServletRequest request, 
    Model model) {
        User user = userDao.getUserByUsername(auth.getName());
        // IMPORTS TAGS
        List<Tag> tags = tagDao.getAllTags();
        model.addAttribute("tags", tags);
        // CREATE NEW BLOG
        Blog newBlog = new Blog();
        newBlog.setTitle(request.getParameter("title"));
        newBlog.setContent(request.getParameter("content"));
        newBlog.setPhoto(request.getParameter("photo"));
        // GETS LIST OF TAGS FROM HTML
        String[] tagNames = (request.getParameter("name")).split(",");
        // STORED TAGS FROM HTML TO ARRAYLIST
        List<Tag> listTags = new ArrayList();
        for (String name : tagNames) {
            if (!name.equals("")) {
                tagDao.createTags(name); // CREATE TAG IF DOESN'T EXIST
                listTags.add(tagDao.getTagByName(name));
            }
        }
        // SETS TAGS AND USER ID TO NEW BLOG
        newBlog.setTags(listTags);
        newBlog.setUserID(user.getUserID());
        //String photo = !request.getParameter("photo").equals("") ? request.getParameter("photo") : newBlog.getPhoto();
        if (principal.getName().equals("admin")) {
            if (!request.getParameter("post-date").equals("")) {
                LocalDate postDate = LocalDate.parse(request.getParameter("post-date"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
                String text = postDate.format(formatter);
                newBlog.setPostDate(LocalDate.parse(text, formatter));
            }
            if (!request.getParameter("expire-date").equals("")) {
                LocalDate expirationDate = LocalDate.parse(request.getParameter("expire-date"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
                String text = expirationDate.format(formatter);
                newBlog.setExpirationDate(LocalDate.parse(text, formatter));
            }
        }
        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(newBlog);
        if (result.hasErrors() || blogDao.createBlog(newBlog) == null) {
            logger.info("no");
            model.addAttribute("errors", violations);
            return "writeBlog";
        } else {
            return "redirect:/blogs";
        }
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

    private String parseHTML(String content) {
        return content.replaceAll("<.*?>", "");

    }

    @PostMapping("editBlog")
    public String updateBlog(HttpServletRequest request, Model model) {
        // FETCHES SELECTED BLOG TO BE EDITED
        Blog blog = blogDao.getBlogByID(Integer.parseInt(request.getParameter("blogID").trim()));
        // SETS NEW INFO TO SELECTED BLOG
        blog.setTitle(request.getParameter("title"));
        blog.setContent(request.getParameter("content"));
        logger.info("LOGGER: " + request.getParameter("content"));
        blog.setApproved(false);
        // GETS LIST OF TAGS FROM HTML
        String[] tagNames = (request.getParameter("name")).split(",");
        // STORED TAGS FROM HTML TO ARRAYLIST
        logger.info("TAG NAMES: " + String.valueOf(tagNames));
        List<Tag> listTags = new ArrayList<>();
        for (String name : tagNames) {
            if (!name.equals("")) {
                tagDao.createTags(name);
                listTags.add(tagDao.getTagByName(name));
            }
        }
        blog.setTags(listTags);
        String updatedPhoto = !request.getParameter("photo").equals("") ? request.getParameter("photo") : blog.getPhoto();
        blog.setPhoto(updatedPhoto);

        if (request.getParameter("postDate") != null) {blog.setPostDate(LocalDate.parse(request.getParameter("postDate").trim()));}
        if (request.getParameter("expirationDate") != null) {blog.setExpirationDate(LocalDate.parse(request.getParameter("expirationDate").trim()));}

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(blog);
        if (result.hasErrors() || blogDao.updateBlog(blog) == false) {
            logger.info("no");
            model.addAttribute("errors", violations);
            return "editBlog";
        } else {
            return "redirect:/blogs";
        }
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
        List<Blog> myblogs = new ArrayList<>();
        for (Blog blog : blogDao.getAllBlogs()) {
            // DISPLAY ADMIN'S OWN BLOGS
            if (blog.getUserID() == user.getUserID()) {
                myblogs.add(blog);
            }
        }
        model.addAttribute("myblogs", myblogs);
        return "approveBlogs";
    }

}
