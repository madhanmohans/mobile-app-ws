package com.example.spring.mobile_app_ws.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") // http://localhost/8080/users
public class UserController {

    @GetMapping
    public String getUsers(@RequestParam(value = "page") int page, 
                            @RequestParam(value = "limit") int limit) {
        return "get users was called with page " + page + " and limit " + limit;
    }
    
    @GetMapping(path="/{userID}")
    public String getUser(@PathVariable("userID") String userID) {
        return "Get user was called with userID " + userID;
    }
    
    @PostMapping
    public String createUser() {
        return "Create user was called";
    }    
    
    @PutMapping
    public String updateUser() {
        return "Update user was called";
    }    
    
    @DeleteMapping
    public String deleteUser() {
        return "Delete user was called";
    }
}
