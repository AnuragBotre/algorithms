package com.trendcore.spring;

import com.trendcore.spring.beans.A;
import com.trendcore.spring.beans.EmployeeBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanCreationWIthFactoryMethod {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("factory-method-for-bean-creation.xml");


        A a = context.getBean("a", A.class);
        System.out.println(a);

        context.close();
    }

}
