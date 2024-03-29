package practice.docker.core.util.logging;

import org.aspectj.lang.annotation.Pointcut;

public class LoggingPointCut {

    @Pointcut("@annotation(practice.docker.core.util.logging.ApiLogging)")
    public void apiLoggingPointcut() {}
}
