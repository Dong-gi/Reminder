package io.github.donggi.reminder.aop;

import java.util.Collection;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class DtoAspect {
    @Before("execution(public * io.github.donggi.reminder.dao..insert*(..))")
    public void beforeInsert(JoinPoint jp) {
        checkDateColumns(jp);
    }

    @Before("execution(public * io.github.donggi.reminder.dao..update*(..))")
    public void beforeUpdate(JoinPoint jp) {
        checkDateColumns(jp);
    }

    @Before("execution(public * io.github.donggi.reminder.dao..upsert*(..))")
    public void beforeUpsert(JoinPoint jp) {
        checkDateColumns(jp);
    }

    private void checkDateColumns(JoinPoint jp) {
        for (Object arg : jp.getArgs()) {
            if (arg == null)
                continue;
            if (arg.getClass().isArray()) {
                for (Object o : (Object[]) arg)
                    fillDateColumns(o);
            } else if (Collection.class.isInstance(arg))
                ((Collection) arg).forEach(this::fillDateColumns);
            else 
                fillDateColumns(arg);
        }
    }

    @SneakyThrows
    private void fillDateColumns(Object o) {
        Class<?> clazz = o.getClass();
        log.info(clazz.getCanonicalName());
        if (clazz.getCanonicalName().startsWith("io.github.donggi.reminder.dto")) {
            if (clazz.getMethod("getAddDate").invoke(o) == null)
                clazz.getMethod("setAddDate", Date.class).invoke(o, new Date());
            clazz.getMethod("setUpdDate", Date.class).invoke(o, new Date());
        }       
    }
}
