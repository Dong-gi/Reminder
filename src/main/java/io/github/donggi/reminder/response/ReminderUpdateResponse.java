package io.github.donggi.reminder.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReminderUpdateResponse extends ApiResponse {

    private String reminderId;
    private String attachFile;

}
