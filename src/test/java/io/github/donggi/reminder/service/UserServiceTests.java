package io.github.donggi.reminder.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.github.donggi.reminder.enums.ApiResultCode;
import io.github.donggi.reminder.request.UserLoginRequest;
import io.github.donggi.reminder.response.UserLoginResponse;
import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("dev")
@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class UserServiceTests {

    private static UserLoginResponse response;

    @Autowired
    private UserService userService;


    @Test
    @Order(1)
    public void reigsterNewUser() {
        UserLoginRequest request = new UserLoginRequest();
        request.setNickname(new Date().toString());
        request.setPassword(new Date().toString());
        response = userService.register(request);
        assertTrue(response.getApiResultCode() == ApiResultCode.OK);
        log.info(response.getRequestToken());
    }

    @Test
    @Order(2)
    public void refreshSession() {
        UserLoginRequest request = new UserLoginRequest();
        request.setUserId(Long.parseLong(response.getUserId()));
        request.setRequestToken(response.getRequestToken());
        response = userService.login(request);
        assertTrue(response.getApiResultCode() == ApiResultCode.OK);
        log.info(response.getRequestToken());
    }

}
