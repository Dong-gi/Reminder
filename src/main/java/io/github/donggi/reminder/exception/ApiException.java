package io.github.donggi.reminder.exception;

import io.github.donggi.reminder.enums.ApiResultCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ApiResultCode apiResultCode;

    public ApiException(ApiResultCode apiResultCode, String message) {
        super(message);
        this.apiResultCode = apiResultCode;
    }
}
