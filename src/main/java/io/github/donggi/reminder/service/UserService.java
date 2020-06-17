package io.github.donggi.reminder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.github.donggi.reminder.dao.TUserDao;
import io.github.donggi.reminder.dto.TUser;
import io.github.donggi.reminder.dto.TUserSession;
import io.github.donggi.reminder.enums.ApiResultCode;
import io.github.donggi.reminder.exception.ApiException;
import io.github.donggi.reminder.logic.UserLogic;
import io.github.donggi.reminder.request.UserLoginRequest;
import io.github.donggi.reminder.response.UserLoginResponse;
import io.github.donggi.reminder.util.HashUtil;

@Component
public class UserService {

    @Autowired
    private TUserDao tUserDao;

    @Autowired
    private UserLogic userLogic;


    public UserLoginResponse register(UserLoginRequest request) {
        if (tUserDao.isUserExists(request.getNickname()))
            throw new ApiException(ApiResultCode.NICKNAME_ALREADY_EXISTS, "가입하려는 닉네임이 이미 등록됐습니다");

        TUser tUser = userLogic.registerNewUser(request.getNickname(), request.getPassword());
        TUserSession tUserSession = userLogic.registerSession(tUser.getUserId(), request.getAlwaysLogin());

        UserLoginResponse res = new UserLoginResponse();
        res.setUserId(tUserSession.getUserId());
        res.setRequestToken(tUserSession.getNextToken());
        return res;
    }

    public UserLoginResponse login(UserLoginRequest request) {
        boolean tryAutoLogin = request.getRequestToken() != null;
        if (tryAutoLogin) {
            if (request.getUserId() == null)
                throw new ApiException(ApiResultCode.BAD_REQUEST, "userId가 있어야 세션 갱신이 가능합니다");

            TUser tUser = tUserDao.selectByUserId(request.getUserId());
            TUserSession tUserSession = userLogic.refreshSession(tUser.getUserId(), request.getRequestToken(), request.getAlwaysLogin());

            UserLoginResponse res = new UserLoginResponse();
            res.setUserId(tUser.getUserId());
            res.setRequestToken(tUserSession.getNextToken());
            return res;
        }

        if (!tUserDao.isUserExists(request.getNickname()))
            throw new ApiException(ApiResultCode.NO_SUCH_USER, "로그인하려는 유저가 존재하지 않습니다");
        TUser tUser = tUserDao.selectByNickname(request.getNickname());
        if (!tUser.getPwdHash().equals(HashUtil.getUserPwdHash(tUser.getUserId(), request.getNickname(), request.getPassword())))
            throw new ApiException(ApiResultCode.BAD_REQUEST, "비밀번호가 일치하지 않습니다");
        TUserSession tUserSession = userLogic.registerSession(tUser.getUserId(), request.getAlwaysLogin());
        UserLoginResponse res = new UserLoginResponse();
        res.setUserId(tUserSession.getUserId());
        res.setRequestToken(tUserSession.getNextToken());
        return res;
    }

}
