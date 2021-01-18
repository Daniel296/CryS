package com.wade.crys.data.coin;

import com.wade.crys.coin.interfaces.CoinService;
import com.wade.crys.data.coin.interfaces.CoinCollector;
import com.wade.crys.coin.interfaces.CoinRepository;
import com.wade.crys.coin.model.Coin;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoinCollectorJob implements Job {

    private static boolean hasData = false;

    private CoinCollector coinCapAPI;
    private CoinService coinService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            coinCapAPI = (CoinCollector) jobExecutionContext.getScheduler().getContext().get("coinCapAPI");
            coinService = (CoinService) jobExecutionContext.getScheduler().getContext().get("coinService");

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        // first time we should get data for all coins and also logo url
        // after that we just update values for cryptocurrencies
        List<Coin> coins = coinCapAPI.getCoinsFromAPI(!hasData);

        if(!hasData) {

            hasData = true;

            coinService.deleteAllCoins();
            coins.forEach(coin -> coinService.addCoin(coin));
        } else {

            coins.forEach(coin -> coinService.updateCoin(coin));
        }

        System.out.println("Extract data from Coincap.com.... Done!");
    }
}
