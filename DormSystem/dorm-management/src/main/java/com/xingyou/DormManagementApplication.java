package com.xingyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.xingyou.controller.Controller;

@SpringBootApplication
public class DormManagementApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DormManagementApplication.class, args);
        Controller controller = context.getBean(Controller.class);
        controller.start();
    }
}
