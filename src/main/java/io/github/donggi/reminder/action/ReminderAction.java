package io.github.donggi.reminder.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.github.donggi.reminder.annotation.NoTokenAction;
import io.github.donggi.reminder.constant.Texts;
import io.github.donggi.reminder.dao.TUserReminderDao;
import io.github.donggi.reminder.dto.LocalShare;
import io.github.donggi.reminder.request.ReminderRemoveRequest;
import io.github.donggi.reminder.request.ReminderUpdateRequest;
import io.github.donggi.reminder.response.ApiResponse;
import io.github.donggi.reminder.response.ReminderListResponse;
import io.github.donggi.reminder.response.ReminderUpdateResponse;
import io.github.donggi.reminder.service.ReminderService;

@RestController
@RequestMapping("/reminder/**/*")
public class ReminderAction {

    @Autowired
    private TUserReminderDao tUserReminderDao;

    @Autowired
    private ReminderService reminderService;


    @NoTokenAction
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ReminderListResponse list() {
        ReminderListResponse response = new ReminderListResponse();
        response.setList(tUserReminderDao.selectForClient(LocalShare.USER_ID.get()));
        return response;
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public ReminderUpdateResponse update(MultipartHttpServletRequest hRequest) throws IllegalStateException, IOException {
        ReminderUpdateRequest rRequest = new ReminderUpdateRequest();
        String reminderId = hRequest.getParameter(Texts.SERVLET_REMINDER_ID);
        if (reminderId != null)
            rRequest.setReminderId(Long.parseLong(reminderId));
        rRequest.setTitle(hRequest.getParameter(Texts.SERVLET_REMINDER_TITLE));
        rRequest.setAttachFile(hRequest.getFile(Texts.SERVLET_ATTACH_FILE));
        rRequest.setCompleteFlg(Boolean.valueOf(hRequest.getParameter(Texts.SERVLET_REMINDER_COMPLETE_FLG)));
        return reminderService.update(rRequest);
    }

    @RequestMapping(value="/remove", method=RequestMethod.POST)
    public ApiResponse remove(@RequestBody ReminderRemoveRequest request) {
        return reminderService.remove(request);
    }

}
