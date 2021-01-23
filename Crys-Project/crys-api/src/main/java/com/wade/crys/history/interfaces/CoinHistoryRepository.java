package com.wade.crys.history.interfaces;

import java.util.List;

import com.wade.crys.history.model.CoinHistory;

public interface CoinHistoryRepository {

    List<CoinHistory> getCoinHistory(String id);

    void addCoinHistory(CoinHistory coinHistory);

    void deleteHistoryForCoin(String coinId);
}
