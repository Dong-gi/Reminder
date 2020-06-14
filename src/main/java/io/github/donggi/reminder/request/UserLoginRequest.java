package io.github.donggi.reminder.request;

import lombok.Data;

@Data
public class UserLoginRequest {

    private Long userId;
    private String nickname;
    private String password;
    private String requestToken;
    private Boolean alwaysLogin = Boolean.FALSE;

}
