package com.example.backend;

import com.example.backend.controller.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//@SpringBootApplication
public class App {
    public static void main(String[] args) throws Exception {
        //ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        //DVDController controller = context.getBean(DVDController.class);
        DVDController controller = new DVDController();
        controller.run();
    }
}