package io.github.donggi.reminder.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest
class TUserReminderDaoTests {

    @Autowired
    private TUserReminderDao tUserReminderDao;

    @Test
    void selectId() {
        assertTrue(tUserReminderDao.nextId() > 0);
    }

}
