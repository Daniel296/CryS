package com.wade.crys.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wade.crys.history.interfaces.CoinHistoryRepository;
import com.wade.crys.history.interfaces.CoinHistoryService;
import com.wade.crys.history.model.CoinHistory;

@Service
public class CoinHistoryServiceImp implements CoinHistoryService {

    @Autowired
    private CoinHistoryRepository coinHistoryRepository;

    @Override
    public List<CoinHistory> getCoinHistory(String coinId) {

        return coinHistoryRepository.getCoinHistory(coinId);
    }

    @Override
    public void addCoinHistory(List<CoinHistory> coinHistory) {

        int i = 0;
        for(CoinHistory history : coinHistory) {

            coinHistoryRepository.addCoinHistory(history);

            System.out.println(i++ + "---> Finished to insert history for: " + (coinHistory.size() != 0 ? coinHistory.get(0).getCoinId() : "----"));
        }

//        System.out.println("---> Finished to insert history for: " + (coinHistory.size() != 0 ? coinHistory.get(0).getCoinId() : "----"));
    }

    @Override
    public void deleteHistoryForCoin(String coinId) {

        coinHistoryRepository.deleteHistoryForCoin(coinId);
    }

}
