package com.example.spring.mobile_app_ws.userservice;

import com.example.spring.mobile_app_ws.ui.model.request.UserDetailsRequest;
import com.example.spring.mobile_app_ws.ui.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequest createUserRequest);
}
