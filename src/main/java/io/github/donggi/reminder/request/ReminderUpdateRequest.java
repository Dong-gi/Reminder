package io.github.donggi.reminder.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReminderUpdateRequest {

    private Long reminderId;
    private String title;
    private MultipartFile attachFile;
    private boolean completeFlg;

}
