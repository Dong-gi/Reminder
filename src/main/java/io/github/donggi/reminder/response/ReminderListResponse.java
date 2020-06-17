package io.github.donggi.reminder.response;

import java.util.List;

import io.github.donggi.reminder.dto.Reminder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReminderListResponse extends ApiResponse {

    private List<Reminder> list;

}
