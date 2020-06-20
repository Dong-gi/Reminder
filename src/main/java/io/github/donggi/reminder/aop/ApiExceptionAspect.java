package io.github.donggi.reminder.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

        return result;
    }

    private void addApiAccessLog(ApiResultCode result, Exception e) {
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        LocalShare share = new ThreadLocal<LocalShare>().get();
        log.info("{} - {} : {}", servletRequest.getRequestURI(), (share == null)? servletRequest.getRemoteAddr() : LocalShare.userId.get(), result.name());
        if (e != null)
            e.printStackTrace();
    }

}
