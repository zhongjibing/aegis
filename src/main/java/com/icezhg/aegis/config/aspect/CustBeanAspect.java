package com.icezhg.aegis.config.aspect;

import java.util.Arrays;

import com.icezhg.aegis.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustBeanAspect {

    private Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    private Object[] format(Object... args) {
        return args == null ? null :
                Arrays.stream(args).map(arg -> {
                    return arg == null ? null : arg.getClass().getSimpleName() + ":" + JsonUtil.toString(arg);
                }).toArray();
    }

    @Pointcut("execution(* com.icezhg.aegis.config.custom..*.*(..))")
    public void custPointcut() {
    }

    @Pointcut("execution(* com.icezhg.aegis.controller.UserController.listAllUsers())")
    public void controller() {
    }

    @Around("custPointcut() || controller()")
    public Object aroundCustPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = getLogger(joinPoint.getTarget().getClass());
        logger.info("exec {}, args: {}", joinPoint.getSignature().getName(), format(joinPoint.getArgs()));
        Object proceed = joinPoint.proceed();
        logger.info("exec {}, result {}", joinPoint.getSignature().getName(), format(proceed));
        return proceed;
    }

}
