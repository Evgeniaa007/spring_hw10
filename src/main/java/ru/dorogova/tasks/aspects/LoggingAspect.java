package ru.dorogova.tasks.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    /**
     * Метод, позволяющий логировать выполнение методов из service.
     * То есть уведомляет о начале выполнения метода и времени выполнения по окончанию
     * @param joinPoint метод
     */
    @Around("@annotation(ru.dorogova.tasks.aspects.TrackUserActions)")
    public Object trackUserAction(ProceedingJoinPoint joinPoint) throws Throwable{

        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        String ANSI_RESET = "\u001B[0m"; // ANSI Escape Code для сброса цвета
        String myColorStart = "\u001B[43;30m";  // Черный текст на жёлтом фоне
        String myColor1 = "\u001B[32m";

        logger.info(myColorStart + "Method " + methodName + ANSI_RESET + myColor1 +
                " will be executed with arguments: " + Arrays.toString(methodArgs));

        // вычисление времени выполннения метода
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        logger.info(myColorStart + "Method " + methodName + ANSI_RESET + myColor1 +
                " has been executed and it took " + elapsedTime + " ms");

        return result;
    }

}
