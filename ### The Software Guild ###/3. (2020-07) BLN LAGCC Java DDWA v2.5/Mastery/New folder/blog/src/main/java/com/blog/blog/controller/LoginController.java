package com.blog.blog.controller;

import javax.servlet.http.HttpServletRequest;

import com.blog.blog.memory.*;
import com.blog.blog.models.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    UserDao userDao;
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);



    @GetMapping("login")
    public String getLogin() {
        logger.info("Start login.");
        return "login";
    }

}
