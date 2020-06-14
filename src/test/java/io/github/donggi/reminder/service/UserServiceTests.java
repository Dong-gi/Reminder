package io.github.donggi.reminder.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
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
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    public void reigsterNewUser() {
        UserLoginRequest request = new UserLoginRequest();
        request.setNickname(new Date().toString());
        request.setPassword(new Date().toString());
        UserLoginResponse response = userService.register(request);
        assertTrue(response.getApiResultCode() == ApiResultCode.OK);
        log.info(response.getRequestToken());
    }

    @Test
    public void refreshSession() {
        UserLoginRequest request = new UserLoginRequest();
        request.setUserId(8386942079666657550L);
        request.setRequestToken("re37sdxlndjf2dr994bd4yy7h7n45dnfdz3d23fenj7dnx6b");
        UserLoginResponse response = userService.login(request);
        assertTrue(response.getApiResultCode() == ApiResultCode.OK);
        log.info(response.getRequestToken());
    }

}
