package com.carrefour.cartmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class CartManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartManagementApplication.class, args);
    }

}
