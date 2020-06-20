package io.github.donggi.reminder.dto;

import javax.servlet.http.HttpSession;

public class LocalShare {
    public static ThreadLocal<Long> userId = new ThreadLocal<>();
    public static ThreadLocal<HttpSession> session = new ThreadLocal<>();
}
