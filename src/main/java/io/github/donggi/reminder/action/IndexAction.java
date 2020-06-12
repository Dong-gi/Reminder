package io.github.donggi.reminder.action;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexAction {
    @RequestMapping("/*")
    public String index() {
        return String.format("Hello! %s", new Date());
    }
}
