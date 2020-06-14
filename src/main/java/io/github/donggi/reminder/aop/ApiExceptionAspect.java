package io.github.donggi.reminder.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import io.github.donggi.reminder.enums.ApiResultCode;
import io.github.donggi.reminder.exception.ApiException;
import io.github.donggi.reminder.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ApiExceptionAspect {
    @Around("execution(public * io.github.donggi.reminder.action..*(..))")
    public Object aroundAction(ProceedingJoinPoint jp) throws Throwable {
        try {
            return jp.proceed();
        } catch (ApiException e) {
            log.error(e.getMessage());
            return new ApiResponse(e.getApiResultCode());
        } catch (Exception e) {
            return new ApiResponse(ApiResultCode.INTERNAL_ERROR);
        }
    }
}
