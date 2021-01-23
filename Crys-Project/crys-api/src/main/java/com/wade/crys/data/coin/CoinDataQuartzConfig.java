package com.wade.crys.data.coin;

import com.wade.crys.coin.interfaces.CoinService;
import com.wade.crys.data.coin.interfaces.CoinCollector;
import com.wade.crys.coin.interfaces.CoinRepository;
import com.wade.crys.data.coin.CoinCollectorJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoinDataQuartzConfig {

    @Autowired
    private CoinCollector coinCapAPI;

    @Autowired
    private CoinService coinService;

    @Bean
    public Scheduler scheduler() throws SchedulerException {

        Scheduler scheduler =  new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail(), trigger());
        scheduler.getContext().put("coinCapAPI", coinCapAPI);
        scheduler.getContext().put("coinService", coinService);
        scheduler.start();

        return scheduler;
    }

    private JobDetail jobDetail() {

        return JobBuilder.newJob().ofType(CoinCollectorJob.class)
                .withIdentity("Quartz_Coin_Data_Collector_Job")
                .withDescription("Invoke Coin Collector Job service...")
                .build();
    }

    private Trigger trigger() {

        return TriggerBuilder.newTrigger()
                .withIdentity("Quartz_Coin_Data_Collector_Job")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(60)
                        .repeatForever())
                .build();
    }
}
