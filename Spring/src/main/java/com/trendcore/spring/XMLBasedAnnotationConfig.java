package com.trendcore.spring;

import com.trendcore.spring.beans.DepartmentBean;
import com.trendcore.spring.beans.EmployeeBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Anurag
 */
public class XMLBasedAnnotationConfig {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("auto-wiring.xml");

        EmployeeBean employee = context.getBean("employee", EmployeeBean.class);

        System.out.println(employee);
        System.out.println(employee.getDepartmentBean().getName());

        DepartmentBean d = context.getBean("humanResource",DepartmentBean.class);
        System.out.println("Get Department Bean :- " + d.getName());

        context.close();
    }

}
