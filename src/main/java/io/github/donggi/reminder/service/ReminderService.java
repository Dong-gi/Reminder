package io.github.donggi.reminder.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.github.donggi.reminder.dao.TUserReminderDao;
import io.github.donggi.reminder.dto.LocalShare;
import io.github.donggi.reminder.dto.TUserReminder;
import io.github.donggi.reminder.enums.ApiResultCode;
import io.github.donggi.reminder.enums.CommonFlag;
import io.github.donggi.reminder.exception.ApiException;
import io.github.donggi.reminder.request.ReminderRemoveRequest;
import io.github.donggi.reminder.request.ReminderUpdateRequest;
import io.github.donggi.reminder.response.ApiResponse;
import io.github.donggi.reminder.response.ReminderUpdateResponse;
import io.github.donggi.reminder.util.HashUtil;

@Component
public class ReminderService {

    @Autowired
    private TUserReminderDao tUserReminderDao;


    public ReminderUpdateResponse update(ReminderUpdateRequest request) throws IllegalStateException, IOException {
        if (request.getTitle().length() < 1)
            throw new ApiException(ApiResultCode.BAD_REQUEST, "일정 제목이 없습니다");

        TUserReminder tUserReminder;
        if (request.getReminderId() == null) {
            request.setReminderId(tUserReminderDao.nextId());
            tUserReminder = new TUserReminder();
            tUserReminder.setReminderId(request.getReminderId());
            tUserReminder.setUserId(LocalShare.USER_ID.get());
            tUserReminder.setDelFlg(CommonFlag.OFF);
        } else
            tUserReminder = tUserReminderDao.selectByReminderId(request.getReminderId());
        tUserReminder.setTitle(request.getTitle());
        tUserReminder.setCompleteFlg(CommonFlag.valueOf(request.isCompleteFlg()));
        if (request.getAttachFile() != null) {
            String originalName = request.getAttachFile().getOriginalFilename();
            String ext = "";
            if (originalName.contains(".") && !originalName.endsWith(".")) {
                String[] tmp = originalName.split("\\.");
                ext = '.' + tmp[tmp.length - 1];
            }

            Calendar now = new GregorianCalendar();
            File destFile = null;
            do {
                String path = String.format("img/%d/%d/%d/%s%s", 
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH),
                        HashUtil.nextFileName(tUserReminder.getUserId(), originalName),
                        ext);
                tUserReminder.setAttachFile(path);
                destFile = new File(String.format("%s/static/%s", Paths.get("").toAbsolutePath(), path));
            } while (destFile.exists());
            destFile.getParentFile().mkdirs();
            request.getAttachFile().transferTo(destFile);
        }
        tUserReminderDao.upsert(tUserReminder);

        ReminderUpdateResponse res = new ReminderUpdateResponse();
        res.setReminderId(tUserReminder.getReminderId().toString());
        res.setAttachFile(tUserReminder.getAttachFile());
        return res;
    }
    
    public ApiResponse remove(ReminderRemoveRequest request) {
        try {
            tUserReminderDao.delete(tUserReminderDao.selectByReminderId(request.getReminderId()));
        } catch (NoSuchElementException e) {
            throw new ApiException(ApiResultCode.BAD_REQUEST, String.format("일치하는 일정(%d)이 없습니다", request.getReminderId()));
        }
        return new ApiResponse(); 
    }

}
