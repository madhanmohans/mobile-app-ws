package com.example.spring.mobile_app_ws.exceptions;

import java.io.Serial;

public class UserServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public UserServiceException(String message) {
        super(message);
    }
}
