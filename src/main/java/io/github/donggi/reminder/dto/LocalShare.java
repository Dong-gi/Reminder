package io.github.donggi.reminder.dto;

import javax.servlet.http.HttpSession;

import lombok.Data;

@Data
public class LocalShare {
    private Long userId;
    private HttpSession session;
}
