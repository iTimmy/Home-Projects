package com.example.backend;

import com.example.backend.controller.*;
import com.example.backend.dao.*;
import com.example.backend.view.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//@SpringBootApplication
public class App {
    public static void main(String[] args) throws Exception {
        //ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        //DVDController controller = context.getBean(DVDController.class);
        UserIO io = new UserIOImpl();
        DVDView view = new DVDView(io);
        DVDdao dao = new DaoImpl();
        DVDController controller = new DVDController(view, dao);
        controller.run();
    }
}