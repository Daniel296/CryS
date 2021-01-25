package com.wade.crys.data.history;

import java.util.List;
import java.util.stream.Collectors;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import com.wade.crys.coin.interfaces.CoinService;
import com.wade.crys.coin.model.Coin;
import com.wade.crys.data.coin.interfaces.CoinCollector;
import com.wade.crys.data.history.interfaces.CoinHistoryCollector;
import com.wade.crys.history.interfaces.CoinHistoryService;
import com.wade.crys.history.model.CoinHistory;

@Component
@DisallowConcurrentExecution
public class CoinHistoryCollectorJob implements Job {

    private CoinHistoryCollector coinHistoryCollector;

    private CoinHistoryService coinHistoryService;

    private CoinService coinService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try {

            coinHistoryCollector = (CoinHistoryCollector) jobExecutionContext.getScheduler().getContext().get("coinHistoryCollector");
            coinHistoryService = (CoinHistoryService) jobExecutionContext.getScheduler().getContext().get("coinHistoryService");
            coinService = (CoinService) jobExecutionContext.getScheduler().getContext().get("coinService");

        } catch (SchedulerException e) {

            e.printStackTrace();
        }

        List<Coin> coins = coinService.getAllCoinsOrderByRankAsc();
        for(int i = 0; i < coins.size(); i++) {

            List<CoinHistory> coinHistory = coinHistoryCollector.getCoinsHistoryFromAPI(coins.get(i).getId());

            coinHistoryService.deleteHistoryForCoin(coins.get(i).getId());
            coinHistoryService.addCoinHistory(coinHistory);
        }

    }
}
