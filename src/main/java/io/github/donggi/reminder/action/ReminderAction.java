package io.github.donggi.reminder.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.donggi.reminder.annotation.NoTokenAction;
import io.github.donggi.reminder.dao.TUserReminderDao;
import io.github.donggi.reminder.dto.LocalShare;
import io.github.donggi.reminder.response.ReminderListResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/reminder/**/*")
@Slf4j
public class ReminderAction {

    @Autowired
    private TUserReminderDao tUserReminderDao;

    @NoTokenAction
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ReminderListResponse list() {
        ReminderListResponse response = new ReminderListResponse();
        response.setList(tUserReminderDao.selectForClient(new ThreadLocal<LocalShare>().get().getUserId()));
        return response;
    }

}