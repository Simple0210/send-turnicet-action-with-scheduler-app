package com.example.employeactionscheduleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmployeActionScheduleAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeActionScheduleAppApplication.class, args);
    }

}
