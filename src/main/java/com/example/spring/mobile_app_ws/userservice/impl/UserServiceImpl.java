package com.example.spring.mobile_app_ws.userservice.impl;

import com.example.spring.mobile_app_ws.ui.model.request.UserDetailsRequest;
import com.example.spring.mobile_app_ws.ui.model.response.UserRest;
import com.example.spring.mobile_app_ws.userservice.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    Map<String, UserRest> users;

    @Override
    public UserRest createUser(UserDetailsRequest createUserRequest) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail(createUserRequest.getEmail());
        returnValue.setFirstName(createUserRequest.getFirstName());
        returnValue.setLastName(createUserRequest.getLastName());

        String userId = UUID.randomUUID().toString();
        returnValue.setUserId(userId);
        if(users == null) users = new HashMap<>();
        users.put(userId, returnValue);
        return returnValue;
    }
}
