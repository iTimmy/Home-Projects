package com.blog.blog.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import com.blog.blog.models.*;

import java.util.HashSet;
import java.util.Set;

import com.blog.blog.memory.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;
    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) {
        logger.info("Running service...");
        com.blog.blog.models.User user = userDao.getUserByUsername(username);
        logger.info(
            "\nfirstname: " + user.getFirstName() +
            "\nlastname: " + user.getLastName() +
            "\nusername: " + user.getUsername() +
            "\npassword: " + user.getPassword() +
            "\nrole: " + user.getRoles() +
            "\nid: " + user.getUserID() + 
            "\nenabled: " + user.isEnabled()
        );
        logger.info("USERNAME: " + username);
        // UserDetails me = User.builder()
        //     .username("timmy")
        //     .password("password")
        //     .roles("USER")
        //     .build();
        // logger.info(
        //     "\nusername: " + me.getUsername() +
        //     "\npassword: " + me.getPassword() +
        //     "\nroles: " + me.getAuthorities()
        // );
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            logger.info("ROLE: " + role.getRole());
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new
            org.springframework.security.core.userdetails.User(
                user.getUsername(), 
                user.getPassword(),
            grantedAuthorities);
    }
    
}
