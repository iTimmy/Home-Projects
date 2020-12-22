package com.example.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("login")
    public String showLoginForm() {
        logger.info("Get login.");
        return "login";
    }

    @PostMapping("login")
    public String postLoginForm(HttpServletRequest request) {
        logger.info("Post login.");
        return "redirect:/home";
    }

}
