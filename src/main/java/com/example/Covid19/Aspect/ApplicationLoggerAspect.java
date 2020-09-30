package com.example.Covid19.Aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class ApplicationLoggerAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	// point cut means where to log
	/*
	 * 
	 * 
	 * @Pointcut("within(com.example.Covid19.controller..*)"
	 * +"within(some other package)")
	 * 
	 */
	@Pointcut("within(com.example.Covid19.controller..*)")
	public void definePackagePointCuts() {
		// empty method just to name the location specified in the point cut
	}
//
//	@After("definePackagePointCuts()")
//	public void afterDoLog(JoinPoint jp) {
//		log.debug(createLogMsg(jp,"Out bound"));
//	}
//
//	@Before("definePackagePointCuts()")
//	public void beforeDoLog(JoinPoint jp) {
//		log.debug(createLogMsg(jp,"In bound"));
//	}
//	
//	
//	public String createLogMsg(JoinPoint jp,String str) {
//		StringBuilder sb=new StringBuilder();
//		sb.append("\n ");
//		sb.append(str+" ---- ");
//		sb.append("[ "+new Date().toGMTString()+" ]"+ " ---- ");
//		sb.append(jp.getSignature().getDeclaringTypeName()+" ---- ");
//		sb.append(jp.getSignature().getDeclaringTypeName()+" ---- ");
//		sb.append(Arrays.toString(jp.getArgs()));
//		return sb.toString();
//	}
//	/*
	
	@SuppressWarnings("deprecation")
	@Around("definePackagePointCuts()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		Object[] array = pjp.getArgs();
		log.info("\n "+new Date().toGMTString()+" "+"method invoked " + className + " : " + methodName + "()" + "arguments : "
				+ mapper.writeValueAsString(array));
		Object object = pjp.proceed();
//		log.info("\n "+new Date().toGMTString()+" "+"method invoked "+ className + " : " + methodName + "()" + "Response : " + mapper.writeValueAsString(object));
		return object;
	}

}
