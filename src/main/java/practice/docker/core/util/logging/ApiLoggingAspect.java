package practice.docker.core.util.logging;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ApiLoggingAspect extends LoggingPointCut {

    private final ApiLogger apiLogger;

    @Before(value = "apiLoggingPointcut()")
    public void apiBeforeLogging(JoinPoint joinPoint) {
        Class<?> returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();
        if (ResponseEntity.class.isAssignableFrom(returnType)) {
            apiLogger.beforeLog(joinPoint);
        }
    }

    @AfterReturning(value = "apiLoggingPointcut()", returning = "response")
    public void apiAfterLogging(ResponseEntity response) {
        apiLogger.afterLog(response);
    }

    @AfterReturning(value = "within(practice.docker.core.exception.SystemExceptionHandler)", returning = "response")
    public void apiThrowException(ResponseEntity response) {
        apiLogger.failLog(response);
    }
}
