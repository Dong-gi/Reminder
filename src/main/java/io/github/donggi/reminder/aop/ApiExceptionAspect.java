package io.github.donggi.reminder.aop;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.github.donggi.reminder.constant.SessionKey;
import io.github.donggi.reminder.dto.LocalShare;
import io.github.donggi.reminder.enums.ApiResultCode;
import io.github.donggi.reminder.exception.ApiException;
import io.github.donggi.reminder.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionAspect {
    private static final Logger ERROR_LOG = LoggerFactory.getLogger("ERROR_LOG");
    
    @Around("execution(public * io.github.donggi.reminder.action..*(..))")
    public Object aroundAction(ProceedingJoinPoint jp) throws Throwable {
        Object result = ((MethodSignature) jp.getSignature()).getReturnType();

        try {
            result = jp.proceed();
            addApiAccessLog(ApiResultCode.OK, null);
        } catch (ApiException e) {
            result = ((Class) result).newInstance();
            ((ApiResponse) result).setApiResultCode(e.getApiResultCode());
            addApiAccessLog(e.getApiResultCode(), e);
        } catch (Exception e) {
            result = ((Class) result).newInstance();
            ((ApiResponse) result).setApiResultCode(ApiResultCode.INTERNAL_ERROR);
            addApiAccessLog(ApiResultCode.INTERNAL_ERROR, e);
        }
        if (ApiResponse.class.isAssignableFrom(result.getClass())
                && LocalShare.SESSION.get() != null
                && LocalShare.SESSION.get().getAttribute(SessionKey.REQUEST_TOKEN) != null)
            ((ApiResponse) result).setRequestToken((String)LocalShare.SESSION.get().getAttribute(SessionKey.REQUEST_TOKEN));
        return result;
    }

    private void addApiAccessLog(ApiResultCode result, Exception e) {
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = null;
        if (LocalShare.SESSION.get() != null
                && LocalShare.SESSION.get().getAttribute(SessionKey.REQUEST_TOKEN) != null)
            token = (String)LocalShare.SESSION.get().getAttribute(SessionKey.REQUEST_TOKEN);
        if (e != null) {
            StringWriter w = new StringWriter();
            e.printStackTrace(new PrintWriter(w));
            ERROR_LOG.error("{}\n{}", token, w.toString());
        }
        log.info("{} - {}({}) : {}", servletRequest.getRequestURI(), (LocalShare.USER_ID.get() == null)? servletRequest.getRemoteAddr() : LocalShare.USER_ID.get(), token, result.name());
    }

}
