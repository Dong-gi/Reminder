package io.github.donggi.reminder.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequest {

    private Long userId;
    private String nickname;
    private String password;
    private String requestToken;
    private Boolean alwaysLogin = Boolean.FALSE;

}
