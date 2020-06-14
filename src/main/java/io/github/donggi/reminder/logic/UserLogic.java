package io.github.donggi.reminder.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.github.donggi.reminder.constant.Texts;
import io.github.donggi.reminder.dao.TUserDao;
import io.github.donggi.reminder.dao.TUserSessionDao;
import io.github.donggi.reminder.dto.TUser;
import io.github.donggi.reminder.dto.TUserSession;
import io.github.donggi.reminder.enums.ApiResultCode;
import io.github.donggi.reminder.enums.CommonFlag;
import io.github.donggi.reminder.exception.ApiException;
import io.github.donggi.reminder.util.HashUtil;
import lombok.NonNull;

@Component
public class UserLogic {

    @Autowired
    private TUserDao tUserDao;
    @Autowired
    private TUserSessionDao tUserSessionDao;


    /**
     * userId는 자동 할당
     */
    public TUser registerNewUser(@NonNull String nickname, @NonNull String password) {
        TUser tUser = new TUser();
        Random random = new Random(System.currentTimeMillis());
        Long userId = random.nextLong();
        while (tUserDao.isUserExists(userId))
            userId = random.nextLong();
        tUser.setUserId(userId);
        tUser.setNickname(nickname);
        tUser.setPwdHash(HashUtil.getUserPwdHash(userId, nickname, password));
        tUserDao.insert(tUser);
        return tUserDao.selectByUserId(userId);
    }

    /**
     * 신규 세션 시작
     */
    public TUserSession registerSession(TUser tUser, boolean alwaysLogin) {
        Calendar limitDate = new GregorianCalendar();
        if (alwaysLogin)
            limitDate.set(Calendar.YEAR, 2222);
        else
            limitDate.add(Calendar.MINUTE, 30);

        TUserSession tUserSession = new TUserSession();
        tUserSession.setUserId(tUser.getUserId());
        tUserSession.setNextToken(HashUtil.nextToken(tUser.getUserId(), limitDate.getTime()));
        tUserSession.setTokenLimitDate(limitDate.getTime());
        tUserSession.setAlwaysLoginFlg(CommonFlag.valueOf(alwaysLogin));
        tUserSessionDao.upsert(tUserSession);

        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = servletRequest.getSession();
        session.setAttribute(Texts.userId, tUserSession.getUserId());
        session.setAttribute(Texts.requestToken, tUserSession.getNextToken());
        return tUserSession;
    }

    /**
     * 기존 세션 갱신
     */
    public TUserSession refreshSession(TUser tUser, String requestToken, boolean alwaysLogin) {
        TUserSession tUserSession = tUserSessionDao.select(tUser.getUserId());
        if (!tUserSession.getNextToken().equals(requestToken))
            throw new ApiException(ApiResultCode.BAD_REQUEST, "토큰이 일치하지 않습니다");
        if (tUserSession.getTokenLimitDate().before(new Date()))
            throw new ApiException(ApiResultCode.REQUEST_TOKEN_EXPIRED, "만료된 토큰으로 세션을 갱신할 수 없습니다");

        Calendar limitDate = new GregorianCalendar();
        if (alwaysLogin)
            limitDate.set(Calendar.YEAR, 2222);
        else
            limitDate.add(Calendar.MINUTE, 30);
        tUserSession.setNextToken(HashUtil.nextToken(tUser.getUserId(), limitDate.getTime()));
        tUserSession.setTokenLimitDate(limitDate.getTime());
        tUserSession.setAlwaysLoginFlg(CommonFlag.valueOf(alwaysLogin));
        tUserSessionDao.upsert(tUserSession);

        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = servletRequest.getSession();
        session.setAttribute(Texts.userId, tUserSession.getUserId());
        session.setAttribute(Texts.requestToken, tUserSession.getNextToken());
        return tUserSession;
    }

}
