package com.wade.crys;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrysApplication {

    @Autowired
    private static Scheduler scheduler;

    @Autowired
    private static Scheduler coinHistoryScheduler;

    public static void main(String[] args) {
        SpringApplication.run(CrysApplication.class, args);
    }
}
