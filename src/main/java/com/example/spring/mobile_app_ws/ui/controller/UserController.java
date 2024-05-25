package com.example.spring.mobile_app_ws.ui.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.mobile_app_ws.ui.model.request.UserDetailsRequest;
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
    public ResponseEntity<UserRest> getUser(@PathVariable("userID") String userID) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail("example@email.com");
        returnValue.setFirstName("Alex");
        returnValue.setLastName("Hormozzi");
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK); // JSON default return type
    }
    
    @PostMapping(consumes = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
        }, produces = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
        })
    public ResponseEntity<UserRest> createUser(@RequestBody UserDetailsRequest createUserRequest) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail(createUserRequest.getEmail());
        returnValue.setFirstName(createUserRequest.getFirstName());
        returnValue.setLastName(createUserRequest.getLastName());
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
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
