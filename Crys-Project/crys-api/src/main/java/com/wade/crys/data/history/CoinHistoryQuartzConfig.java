package com.wade.crys.data.history;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wade.crys.coin.interfaces.CoinService;
import com.wade.crys.data.history.interfaces.CoinHistoryCollector;
import com.wade.crys.history.interfaces.CoinHistoryService;

@Configuration
public class CoinHistoryQuartzConfig {

    @Autowired
    private CoinHistoryCollector coinHistoryCollector;

    @Autowired
    private CoinHistoryService coinHistoryService;

    @Autowired
    private CoinService coinService;

    @Bean
    public Scheduler coinHistoryScheduler() throws SchedulerException {

        Scheduler scheduler =  new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(coinHistoryJobDetail(), coinHistoryTrigger());
        scheduler.getContext().put("coinHistoryCollector", coinHistoryCollector);
        scheduler.getContext().put("coinHistoryService", coinHistoryService);
        scheduler.getContext().put("coinService", coinService);
        scheduler.startDelayed(60);
        scheduler.start();

        return scheduler;
    }

    private JobDetail coinHistoryJobDetail() {

        return JobBuilder.newJob().ofType(CoinHistoryCollectorJob.class)
                .withIdentity("Quartz_Coin_History_Data_Collector_Job")
                .withDescription("Invoke Coin History Collector Job service...")
                .build();
    }

    private Trigger coinHistoryTrigger() {

        return TriggerBuilder.newTrigger()
                .withIdentity("Quartz_Coin_History_Data_Collector_Job")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInHours(1)
                        .repeatForever())
                .build();
    }

}
