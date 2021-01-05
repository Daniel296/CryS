package com.wade.crys;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrysApplication {

    @Autowired
    private static Scheduler scheduler;

    public static void main(String[] args) {
        SpringApplication.run(CrysApplication.class, args);
    }

}
