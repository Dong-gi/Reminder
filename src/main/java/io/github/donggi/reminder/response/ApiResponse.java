package io.github.donggi.reminder.response;

import io.github.donggi.reminder.enums.ApiResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private ApiResultCode apiResultCode = ApiResultCode.OK;

}
