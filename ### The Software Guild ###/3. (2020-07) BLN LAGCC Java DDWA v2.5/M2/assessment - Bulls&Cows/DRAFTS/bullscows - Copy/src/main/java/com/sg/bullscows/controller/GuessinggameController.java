package com.sg.bullscows.controller;

import com.sg.bullscows.dto.*;
import com.sg.bullscows.service.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PTGIN
 */
@RestController
@RequestMapping("")
public class GuessinggameController {
int idnumber = 0;
List<User> users = new ArrayList<>();
User jhoan = new User();
List<game> games = new ArrayList<>();
@Autowired
GuessinggameServiceLayer service;
    
@GetMapping("/users")
public List<User> sayHello() {
    return users;
}

@GetMapping("/user/{id}") // refering to DTO for info,  getting from the database 
public User getUserByID(@PathVariable int id) {
    for (int i = 0; i < users.size(); i++) {
        if (users.get(i).getId() == id) {
            return users.get(i);
        }
    }
    return null;
}
@PostMapping("/user") //create information,  adding to database
public void addUser(@RequestBody User user) {
    users.add(user);
}

@PostMapping("/begin") // start game creates information, adds information, select post option on post has to be a post and has to be url ("/begin")  ("/begin") is url
public int beginGame(){ 
    game newgame = new game();
    return service.beginGame(newgame);
};

@GetMapping("/games") // has to be a get and has to be url "/games",  "/games" is url     get game data from database 
public List<game> getAllGames(){
return service.getAllGames();
};

@GetMapping("/games/{id}") //get gameId date from Database
 public game getGameById(@PathVariable int id) { // gets the paramater from the URL ("/games/{id}"),  
   return service.getGameById(id);
 };

 @GetMapping("/round/{id}") //get gameId date from Database
 public List<round> getRoundGameById(@PathVariable int id) { // gets the paramater from the URL ("/games/{id}"),  
   return service.getRoundGameById(id);
 };
 
 @PostMapping("/guess") // start game creates information, adds information, select post option on post has to be a post and has to be url ("/begin")  ("/begin") is url
 public round newround(@RequestBody round newround){
return service.newround(newround);
 };
// this controller is a special type of controller that communicates with websites 





    
}
