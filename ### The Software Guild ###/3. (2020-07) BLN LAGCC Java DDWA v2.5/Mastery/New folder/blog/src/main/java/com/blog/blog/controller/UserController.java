package com.blog.blog.controller;

import java.io.File;
import java.security.Principal;
import java.util.*;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {
    
    @Autowired
    UserDao userDao;
    @Autowired
    BlogDao blogDao;
    @Autowired
    ProfileDao profileDao;
    private final String directory = "assets";
    private static Logger logger = LoggerFactory.getLogger(UserController.class);



    @GetMapping("myblogs")
    public String myBlogs(Authentication auth, Model model) {
        User user = userDao.getUserByUsername(auth.getName());
        logger.info(auth.getName());
        logger.info(String.valueOf(user.getProfile()));
        List<Blog> myblogs = new ArrayList<>();
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
          logger.info("\nProfileID: " +
            String.valueOf(user.getProfile().getProfileID()) +
            "\nICON: " + user.getProfile().getIcon() +
            "\nCOVER: " + user.getProfile().getCover() +
            "\nWALLPAPER: " + user.getProfile().getWallpaper());
        model.addAttribute("myblogs", myblogs);
        model.addAttribute("blogs", blogsWaitingForApproval);
        model.addAttribute("user", user);
        return "myblogs";
    }

    @PostMapping("updateUser")
    public String updateUser(Principal principal, HttpServletRequest request) {
        User user = userDao.getUserByUsername(principal.getName());
        Profile profile = user.getProfile();

        // UPDATE PROFILE \\
        String updatedIcon = !request.getParameter("icon").equals("") ? request.getParameter("icon") : user.getProfile().getIcon();
        profile.setIcon(updatedIcon);
        String updatedCover = !request.getParameter("user-cover").equals("") ? request.getParameter("user-cover") : user.getProfile().getCover(); 
        profile.setCover(updatedCover);
        String updatedWallpaper = !request.getParameter("wallpaper").equals("") ? request.getParameter("wallpaper") : user.getProfile().getWallpaper();
        profile.setWallpaper(updatedWallpaper);
        profileDao.updateProfile(profile);
        user.setProfile(profile);

        // UPDATE USER INFO \\
        String updatedFirstName = !request.getParameter("firstname").equals("") ? request.getParameter("firstname") : user.getFirstName();
        user.setFirstName(updatedFirstName);
        String updatedLastName = !request.getParameter("lastname").equals("") ? request.getParameter("lastname") : user.getLastName();
        user.setLastName(updatedLastName);
        String updatedPassword = !request.getParameter("password").equals("") ? request.getParameter("password") : user.getPassword();
        user.setPassword(updatedPassword);

        status_updateUser(user);
        userDao.updateUser(user);

        return "redirect:/myblogs";
    }

    private void status_updateUser(User user) {
        logger.info("\n____________________________________________________" +
            "\n-----------USER-INFORMATION-----------" +
            "\nID: " + user.getUserID() +
            "\nUSERNAME: " + user.getUsername() +
            "\nFIRST-NAME: " + user.getFirstName() +
            "\nLAST-NAME: " + user.getLastName() +
            "\nPASSWORD: " + user.getPassword() +
            "\n-----------USER-PROFILE-----------" +
            "\nProfileID: " + user.getProfile().getProfileID() +
            "\nICON: " + user.getProfile().getIcon() +
            "\nCOVER: " + user.getProfile().getCover() +
            "\nWALLPAPER: " + user.getProfile().getWallpaper() +
            "\n____________________________________________________"
        );
    }

}
