package io.github.donggi.reminder.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.donggi.reminder.annotation.NoAuthAction;
import io.github.donggi.reminder.request.UserLoginRequest;
import io.github.donggi.reminder.response.UserLoginResponse;
import io.github.donggi.reminder.service.UserService;

@RestController
@RequestMapping("/user/**/*")
public class UserAction {

    @Autowired
    private UserService userService;

    @NoAuthAction
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public UserLoginResponse register(@RequestBody UserLoginRequest request) {
        return userService.register(request);
    }

    @NoAuthAction
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public UserLoginResponse login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

}
