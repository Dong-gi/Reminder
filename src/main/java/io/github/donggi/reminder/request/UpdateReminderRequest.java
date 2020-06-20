package io.github.donggi.reminder.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class UpdateReminderRequest {

    private Long reminderId;
    private String title;
    private MultipartFile attachFile;
    private boolean completeFlg;

}
