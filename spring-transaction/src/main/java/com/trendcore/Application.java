package com.trendcore;

import com.trendcore.model.Greeting;
import com.trendcore.service.GreetingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Collection;
import java.util.List;

/**
 * Created by Anurag
 */
@SpringBootApplication
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        GreetingService greetingService = context.getBean(GreetingService.class);

        Collection<Greeting> greetingList = greetingService.findAll();

        greetingList.forEach(greeting -> System.out.println(greeting.getId() + " " + greeting.getText()));

        Greeting one = greetingService.findOne(1L);
        System.out.println(one.getId() + " " + one.getText());

        for (int i = 0; i < 10; i++) {
            greetingService.create(getGreetingBean());
        }
    }

    private static Greeting getGreetingBean() {
        Greeting g = new Greeting();
        g.setText("New Greeting!");
        return g;
    }

}
