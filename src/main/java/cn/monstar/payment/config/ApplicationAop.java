/*
 * System Name    : XXXXX
 * SubSystem Name : Product
 * Class Name     : WebControllerAop
 * Description    : XXXXX
 */

package cn.monstar.payment.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Revision ：
 * Rev -- Date---------Name------------Note
 * 1.0    2017.11.01   MONSTAR-LAB-CN  Created
 */
@Component
@Aspect
public class ApplicationAop {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void executeService(){

    }

    @Pointcut("execution(* cn.monstar.payment.web.controller.*.*(..))")
    public void executeController(){

    }

    @Before("executeService()")
    public void doBeforeAdviceService(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        logger.info("BEGIN Service: {}#{}", signature.getDeclaringTypeName(), signature.getName());
        logger.info("Param={}", joinPoint.getArgs());
    }

    @Before("executeController()")
    public void doBeforeAdviceController(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            logger.info("url: {} start of execution", request.getRequestURL());
        }
        Signature signature = joinPoint.getSignature();
        logger.info("BEGIN Controller: {}#{}", signature.getDeclaringTypeName(), signature.getName());
        logger.info("Param={}", joinPoint.getArgs());

    }

    @After("executeService()")
    public void doEndAdviceService(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        logger.info("END Service: {}#{}", signature.getDeclaringTypeName(), signature.getName());
    }

    @After("executeController()")
    public void doEndAdviceController(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        logger.info("END Controller: {}#{}", signature.getDeclaringTypeName(), signature.getName());
    }
}