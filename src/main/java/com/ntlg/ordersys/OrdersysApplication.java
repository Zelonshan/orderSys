package com.ntlg.ordersys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OrdersysApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersysApplication.class, args);
    }

}
