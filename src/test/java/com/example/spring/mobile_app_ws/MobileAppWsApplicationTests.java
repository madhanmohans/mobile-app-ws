package com.example.spring.mobile_app_ws;

import com.example.spring.mobile_app_ws.ui.model.request.UserDetailsRequest;
import com.example.spring.mobile_app_ws.ui.model.response.UserRest;
import com.example.spring.mobile_app_ws.userservice.impl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest
public class MobileAppWsApplicationTests {

	@MockBean
    private UserServiceImpl userService;

    private Map<String, UserRest> users;

	@BeforeEach
    public void setUp() {
        users = new HashMap<>();

        UserRest user1 = new UserRest();
        user1.setUserId("1");
        user1.setFirstName("Alex");
        user1.setLastName("Hormozzi");

        UserRest user2 = new UserRest();
        user2.setUserId("2");
        user2.setFirstName("Jack");
        user2.setLastName("Williams");

        users.put("1", user1);
        users.put("2", user2);

        Mockito.when(userService.createUser(Mockito.any(UserDetailsRequest.class))).thenAnswer(invocation -> {
            UserDetailsRequest request = invocation.getArgument(0);
            UserRest user = new UserRest();
            user.setUserId("3");
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            users.put(user.getUserId(), user);
            return user;
        });
    }
}
