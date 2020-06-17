package io.github.donggi.reminder.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Reminder {
    private Long reminderId;
    private String title;
    private String attachFile;
    private Integer completeFlg;
    
    public Reminder(TUserReminder reminder) {
        this.reminderId = reminder.getReminderId();
        this.title = reminder.getTitle();
        this.attachFile = reminder.getAttachFile();
        this.completeFlg = reminder.getCompleteFlg();
    }
}