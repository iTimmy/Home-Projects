package com.blog.blog.config;

import com.blog.blog.memory.*;
import com.blog.blog.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetails;
    private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    public void configureGlobalInDB(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    
        .cors().and().csrf().disable()
        .authorizeRequests()
            .antMatchers("/approveBlog", "/userManagement", "/deleteTags").hasRole("ADMIN")
            .antMatchers("/writeBlog", "/addBlog", "/editBlog", "/deleteBlog").hasAnyRole("ADMIN", "USER")
            .antMatchers("/myblogs", "/updateUser").hasAnyRole("ADMIN", "USER", "VIEWER", "GUEST")
            .antMatchers("/", "index", "/blogs", "/register", "/tags", "/viewBlog").permitAll()
            .antMatchers("/css/*", "/js/*", "/assets/*").permitAll()
            .anyRequest().permitAll()
        .and()
        .formLogin()           
            .loginPage("/login")
            .defaultSuccessUrl("/", true)
            .failureUrl("/login?login_error=1")
            .permitAll()
        .and()
        .logout()
            .clearAuthentication(true)
            .invalidateHttpSession(true)
            .deleteCookies()
            .logoutSuccessUrl("/blogs")
            .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}