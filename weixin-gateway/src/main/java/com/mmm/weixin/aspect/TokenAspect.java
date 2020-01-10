package com.mmm.weixin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TokenAspect {

	private static final Logger logger = LoggerFactory.getLogger(TokenAspect.class);
	
	@Pointcut("execution(* com.mmm.weixin.controller..*.*(..))")
	public void execute() {
		
	}
	
}
