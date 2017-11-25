package com.trendcore.spring;

import com.trendcore.spring.beans.AnnotatedBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Anurag
 */
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.trendcore.spring"})
public class AnnotationBasedApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationBasedApp.class);

        AnnotatedBean bean = context.getBean(AnnotatedBean.class);

        System.out.println(bean);
        //bean.setId("123");

        //System.out.println(bean.getId());
        //bean.setId("1");

        //bean.businessMethodWithLoggingAspect();

        try {
            bean.exceptionMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }

        context.close();
    }

}
