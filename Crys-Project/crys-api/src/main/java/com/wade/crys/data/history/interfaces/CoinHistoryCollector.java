package com.wade.crys.data.history.interfaces;

import java.util.List;

import com.wade.crys.coin.model.Coin;
import com.wade.crys.history.model.CoinHistory;

public interface CoinHistoryCollector {

    List<CoinHistory> getCoinsHistoryFromAPI(String coinId);
}
