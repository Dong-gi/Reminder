package io.github.donggi.reminder.dao;

import static io.github.donggi.reminder.mapper.TUserReminderDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.donggi.reminder.dto.Reminder;
import io.github.donggi.reminder.dto.TUserReminder;
import io.github.donggi.reminder.enums.CommonFlag;
import io.github.donggi.reminder.mapper.TUserReminderMapper;

@Repository
public class TUserReminderDao {

    @Autowired
    private AtomicLong nextReminderId;
    @Autowired
    private TUserReminderMapper mapper;

    public Long nextId() {
        return nextReminderId.getAndIncrement();
    }

    public TUserReminder selectByReminderId(Long reminderId) {
        return mapper.selectByPrimaryKey(reminderId).get();
    }

    public List<TUserReminder> selectByUserId(Long userId_) {
        return mapper.select(x -> x.where(userId, isEqualTo(userId_)));
    }

    public List<Reminder> selectForClient(Long userId) {
        return selectByUserId(userId).stream().filter(x -> !x.getDelFlg().isOn()).map(Reminder::new).collect(Collectors.toList());
    }

    public int upsert(TUserReminder tUserReminder) {
        if (mapper.selectByPrimaryKey(tUserReminder.getReminderId()).isPresent())
            return mapper.updateByPrimaryKey(tUserReminder);
        return mapper.insert(tUserReminder);
    }

    public int delete(TUserReminder tUserReminder) {
        tUserReminder.setDelFlg(CommonFlag.ON);
        return mapper.updateByPrimaryKey(tUserReminder);
    }

}
