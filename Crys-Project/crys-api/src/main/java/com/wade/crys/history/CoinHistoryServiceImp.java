package com.wade.crys.history;

import com.wade.crys.coin.model.Coin;
import com.wade.crys.history.interfaces.CoinHistoryRepository;
import com.wade.crys.history.interfaces.CoinHistoryService;
import com.wade.crys.history.model.CoinHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinHistoryServiceImp implements CoinHistoryService {

    @Autowired
    private CoinHistoryRepository coinHistoryRepository;

    @Override
    public List<CoinHistory> getCoinHistory(String id) {
        return coinHistoryRepository.getCoinHistory(id);
    }

    @Override
    public void addCoinHistory(List<CoinHistory> coinHistory) {

        int i = 0;
        for(CoinHistory history : coinHistory) {

            coinHistoryRepository.addCoinHistory(history);
            System.out.println(i++ + " Finished to insert coin history... " + (coinHistory.size() != 0 ? coinHistory.get(0).getCoinId() : "----"));

        }


    }

    @Override
    public void deleteAllCoinHistory() {

    }

}
