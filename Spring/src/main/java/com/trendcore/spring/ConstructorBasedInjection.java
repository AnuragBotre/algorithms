package com.trendcore.spring;

import com.trendcore.spring.constructor.BeanA;
import com.trendcore.spring.constructor.BeanB;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Anurag
 */
public class ConstructorBasedInjection {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("constructor-base-injection.xml");

        BeanA beanA = context.getBean("beanA", BeanA.class);
        BeanB beanB = context.getBean("beanB", BeanB.class);

        BeanB beanBWithIdC = context.getBean("beanC", BeanB.class);

        BeanA beanAwithName = context.getBean("beanNameA", BeanA.class);

        System.out.println(beanA + " " + beanA.getBeanB());

        System.out.println(beanB);

        System.out.println(beanAwithName);

        System.out.println(beanBWithIdC);

        context.close();
    }

}
