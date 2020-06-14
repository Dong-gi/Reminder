package io.github.donggi.reminder.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponse extends ApiResponse {

    private Long userId;
    private String requestToken;

}
