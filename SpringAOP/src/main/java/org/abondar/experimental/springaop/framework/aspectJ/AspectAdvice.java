package org.abondar.experimental.springaop.framework.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by abondar on 18.07.16.
 */

@Component
@Aspect
public class AspectAdvice {
    private Logger logger = LoggerFactory.getLogger(org.abondar.experimental.springaop.framework.aspectJ.AspectAdvice.class);

    @Pointcut("execution(* org.abondar.experimental.springaop.framework.aspectJ..foo*(int)) && args(intVal)")
    public void fooExecution(int intVal){

    }

    @Pointcut("bean(aspectDependency)")
    public void inMyDependency(){

    }

    @Before("fooExecution(intVal) && inMyDependency()")
    public void simpleBeforeAdvice(JoinPoint joinPoint, int intVal){
        if (intVal !=100){
            logger.info("Executing: "+joinPoint.getSignature().getDeclaringTypeName()+ " "
            + joinPoint.getSignature().getName() + " argument: "+intVal);
        }
    }

    @Around("fooExecution(intVal) && inMyDependency()")
    public Object simpleAroundAdvice(ProceedingJoinPoint proceedingJoinPoint, int intVal)throws Throwable{
        logger.info("Before execution: "+
        proceedingJoinPoint.getSignature().getDeclaringTypeName()+ " "
        + proceedingJoinPoint.getSignature().getName()
        + " argument: "+intVal);

        Object retVal = proceedingJoinPoint.proceed();

        logger.info("After execution: "+
                proceedingJoinPoint.getSignature().getDeclaringTypeName()+ " "
                + proceedingJoinPoint.getSignature().getName()
                + " argument: "+intVal);

        return retVal;
    }


}
