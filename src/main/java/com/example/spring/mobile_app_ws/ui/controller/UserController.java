package com.example.spring.mobile_app_ws.ui.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.mobile_app_ws.ui.model.response.UserRest;

@RestController
@RequestMapping("/users") // http://localhost/8080/users
public class UserController {

    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1", required = false) int page, 
                            @RequestParam(value = "limit", defaultValue = "50", required = false) int limit,
                            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort
                            ) {
        return "get users was called with page " + page + " and limit " + limit + " and sort " + sort;
    }
    
    @GetMapping(path="/{userID}", produces = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
        }
    )
    public UserRest getUser(@PathVariable("userID") String userID) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail("example@email.com");
        returnValue.setFirstName("Alex");
        returnValue.setLastName("Hormozzi");
        return returnValue; // JSON default return type
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
