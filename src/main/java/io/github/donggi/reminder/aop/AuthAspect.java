package io.github.donggi.reminder.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.github.donggi.reminder.annotation.NoAuthAction;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AuthAspect {
    @Around("execution(public * io.github.donggi.reminder.action..*(..))")
    public Object aroundAction(ProceedingJoinPoint jp) throws Throwable {
        boolean noAuth = ((MethodSignature) jp.getSignature()).getMethod().isAnnotationPresent(NoAuthAction.class);
        if (noAuth)
            return jp.proceed();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info(request.getSession().getId());
        return jp.proceed();
    }
}
