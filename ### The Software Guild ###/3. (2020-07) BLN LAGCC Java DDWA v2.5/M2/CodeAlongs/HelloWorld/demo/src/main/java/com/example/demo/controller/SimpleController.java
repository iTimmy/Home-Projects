package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SimpleController {

    @GetMapping
    public String[] helloWorld() {
        String[] result = { "Hello", "World", "!" };
        return result;
    }

    @PostMapping("/calculate")
    public String[] calculate() {
        String[] results = { "Helloo", "World", "!" };
        return results;
    }

    @DeleteMapping("/resource/{id}")
    public void delete(@PathVariable int id) {
        // This is where we would use our id to delete.
    }
}