package com.trendcore.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by Anurag
 */
@Aspect
@Component
public class AOPAspect {

    /**
     * Argument passed to @Before is nothing but pointcuts
     */
    @Before("execution(public String getId())")
    public void beforeAdvice() {
        System.out.println("Before Advice :- ");
    }

    @Before("args(arg)")
    public void beforeAdvice(JoinPoint joinPoint,String arg){
        System.out.println("Before Advice 123:- " + arg);
    }

    /*@After("execution(* get*())")
    public void afterAdvice(){
        System.out.println("After Advice :- ");
    }

    @After("args(arg)")
    public void afterAdvice(String arg){
        System.out.println("After Advice :- "+arg);
    }*/

    /*@Around(value = "")
    public void aroundAdvice(){

    }*/

    /**
     * @param returnString Note :- ProceedingJoinPoint is only supported for around advice
     * @AfterReturning annotation will take 2 arguments value and returning
     * value = PointCutExpression
     * returning = returnString
     * Value returned by method.
     */
    /*@AfterReturning(value = "execution(* get*())",returning = "returnString")
    public void afterReturningValue(JoinPoint joinPoint,String returnString){
        System.out.println("After returning " + returnString);
    }*/

    /**
     * Custom Annotation.
     */
    @Before("@annotation(com.trendcore.spring.aop.Loggable)")
    public void loggingAspect() {
        System.out.println("Loggin Aspect");
    }

    @AfterThrowing(value = "execution(* exception*())", throwing = "ex")
    public void afterThrowingException(JoinPoint joinPoint,Throwable ex) {
        System.out.println("After throwing Exception." + ex + " " + joinPoint);
    }

    @Around(value = "exceptionPointCut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        /*try {
            System.out.println("Around Advice");
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;*/
        return joinPoint.proceed();
    }

    @Pointcut(value = "execution(* exception*())")
    public void exceptionPointCut(){

    }

}
