package com.trendcore.spring.boot;

import com.trendcore.spring.rest.FirstRestController;
import com.trendcore.spring.rest.RestControllerApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by Anurag
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext springApplication = SpringApplication.run(new Object[]{Application.class},args);
        //springApplication.close();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context){
        return strings -> {
            String beanNames[] = context.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            System.out.println("Inside command line runner..");
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }

}
