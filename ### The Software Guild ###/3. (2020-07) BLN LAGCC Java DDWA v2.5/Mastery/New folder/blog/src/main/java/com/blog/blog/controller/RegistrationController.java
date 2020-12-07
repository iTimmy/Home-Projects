package com.blog.blog.controller;

import javax.servlet.http.HttpServletRequest;

import com.blog.blog.memory.*;
import com.blog.blog.models.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    UserDao userDao;
    private static Logger logger = LoggerFactory.getLogger(RegistrationController.class);



    @GetMapping("register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("register")
    public String postRegister(HttpServletRequest request) {
        BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();
        logger.info("FIRST NAME: " + request.getParameter("firstName"));
        logger.info("LAST NAME: " + request.getParameter("lastName"));
        logger.info("USERNAME: " + request.getParameter("username"));
        logger.info("PASSWORD: " + request.getParameter("password"));
        User newUser = new User();
        newUser.setFirstName(request.getParameter("firstName"));
        newUser.setLastName(request.getParameter("lastName"));
        newUser.setUsername(request.getParameter("username"));
        newUser.setPassword(passwordEncode.encode(request.getParameter("password")));
        userDao.createUser(newUser);
        return "redirect:/blogs";
    }

}
