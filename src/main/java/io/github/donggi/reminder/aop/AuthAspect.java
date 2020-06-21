package io.github.donggi.reminder.aop;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.github.donggi.reminder.annotation.NoAuthAction;
import io.github.donggi.reminder.annotation.NoTokenAction;
import io.github.donggi.reminder.constant.SessionKey;
import io.github.donggi.reminder.constant.Texts;
import io.github.donggi.reminder.dto.LocalShare;
import io.github.donggi.reminder.enums.ApiResultCode;
import io.github.donggi.reminder.exception.ApiException;
import io.github.donggi.reminder.logic.UserLogic;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AuthAspect {

    @Autowired
    private UserLogic userLogic;

    @Around("execution(public * io.github.donggi.reminder.action..*(..))")
    public Object aroundAction(ProceedingJoinPoint jp) throws Throwable {
        boolean noAuth = ((MethodSignature) jp.getSignature()).getMethod().isAnnotationPresent(NoAuthAction.class);
        if (noAuth)
            return jp.proceed();

        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        LocalShare.SESSION.set(servletRequest.getSession());
        if (LocalShare.SESSION.get().getAttribute(SessionKey.USER_ID) == null)
            throw new ApiException(ApiResultCode.REQUEST_TOKEN_EXPIRED, "로그인 필요");
        LocalShare.USER_ID.set((Long)LocalShare.SESSION.get().getAttribute(SessionKey.USER_ID));

        boolean noToken = ((MethodSignature) jp.getSignature()).getMethod().isAnnotationPresent(NoTokenAction.class);
        if (noToken)
            return jp.proceed();

        String clientToken = servletRequest.getHeader(Texts.SERVLET_REQUEST_TOKEN_HEADER).trim();
        LocalShare.SESSION.get().setAttribute(SessionKey.REQUEST_TOKEN, userLogic.refreshSession(LocalShare.USER_ID.get(), clientToken, null).getNextToken());
        log.info("유저[{}] 세션 갱신. {} -> {}", LocalShare.USER_ID.get(), clientToken, LocalShare.SESSION.get().getAttribute(SessionKey.REQUEST_TOKEN));
        return jp.proceed();
    }
}
