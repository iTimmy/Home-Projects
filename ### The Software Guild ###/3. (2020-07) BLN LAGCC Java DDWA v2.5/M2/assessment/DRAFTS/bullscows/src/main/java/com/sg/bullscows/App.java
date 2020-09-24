package com.sg.bullscows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.sg.bullscows.controller.*;


@SpringBootApplication
public class App {
    
    public void run(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
    
}
