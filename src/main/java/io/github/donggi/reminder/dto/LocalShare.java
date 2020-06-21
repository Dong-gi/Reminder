package io.github.donggi.reminder.dto;

import javax.servlet.http.HttpSession;

public class LocalShare {
    public static final ThreadLocal<Long> USER_ID = new ThreadLocal<>();
    public static final ThreadLocal<HttpSession> SESSION = new ThreadLocal<>();
}
