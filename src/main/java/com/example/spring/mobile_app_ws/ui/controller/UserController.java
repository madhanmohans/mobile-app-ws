package com.example.spring.mobile_app_ws.ui.controller;

import com.example.spring.mobile_app_ws.exceptions.UserServiceException;
import com.example.spring.mobile_app_ws.ui.model.request.UpdateUserDetailsRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/users") // http://localhost/8080/users
public class UserController {

    Map<String, UserRest> users;

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

        if(true) throw new NullPointerException("userID is null");

        if(users.containsKey(userID))
            return ResponseEntity.ok(users.get(userID));
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    @PostMapping(consumes = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
        }, produces = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
        })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequest createUserRequest) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail(createUserRequest.getEmail());
        returnValue.setFirstName(createUserRequest.getFirstName());
        returnValue.setLastName(createUserRequest.getLastName());

        String userId = UUID.randomUUID().toString();
        returnValue.setUserId(userId);
        if(users == null) users = new HashMap<>();
        users.put(userId, returnValue);

        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }    
    
    @PutMapping(path = "/{userID}",
            consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> updateUser(@Valid @RequestBody UpdateUserDetailsRequest updateUserRequest, @PathVariable("userID") String userID) {
        if(users.containsKey(userID)) {
            UserRest returnValue = users.get(userID);
            returnValue.setFirstName(updateUserRequest.getFirstName());
            returnValue.setLastName(updateUserRequest.getLastName());
            users.put(userID, returnValue);
            return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
        }
        return new ResponseEntity<UserRest>(HttpStatus.NOT_FOUND);
    }    
    
    @DeleteMapping(path = "/{userID}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userID") String userID) {
        if(users.containsKey(userID)) {
            users.remove(userID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
