package io.github.donggi.reminder.logic;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.donggi.reminder.dao.TUserDao;
import io.github.donggi.reminder.dto.TUser;
import lombok.NonNull;

@Component
public class UserLogic {

    @Autowired
    private TUserDao tUserDao;

    /**
     * userId는 자동 할당
     */
    public TUser registerNewUser(@NonNull String nickname, @NonNull String pwdHash) {
        TUser tUser = new TUser();
        Random random = new Random(System.currentTimeMillis());
        Long userId = random.nextLong();
        while (tUserDao.isUserExists(userId))
            userId = random.nextLong();
        tUser.setUserId(userId);
        tUser.setNickname(nickname);
        tUser.setPwdHash(pwdHash);
        tUserDao.insert(tUser);
        return tUserDao.selectByUserId(userId);
    }

}
