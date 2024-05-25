package com.example.spring.mobile_app_ws.ui.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UpdateUserDetailsRequest {
    @NotEmpty(message = "First name should not be empty")
    @Size(min = 2, max = 20, message = "First name should be at least 2 characters long")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, max = 20, message = "Last name should be at least 2 characters long")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
