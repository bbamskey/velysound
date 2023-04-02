package kr.co.velysound.common.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@ConditionalOnProperty(value = "spring.profiles.include", havingValue = "dev", matchIfMissing = false)
@Component
@Aspect
public class PrintExecTimeAop {
	Logger logger = LoggerFactory.getLogger(PrintExecTimeAop.class);

	@Around("@annotation(PrintExecTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		String method = joinPoint.getSignature().getName();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object proceed = joinPoint.proceed();
		stopWatch.stop();
		logger.info("[시간측정]" + method + ": 메소드 수행시간:" + stopWatch.getTotalTimeSeconds());
		return proceed;
	}
}
