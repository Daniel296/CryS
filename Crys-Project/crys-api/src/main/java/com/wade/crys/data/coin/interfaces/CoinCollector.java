package com.wade.crys.data.coin.interfaces;

import com.wade.crys.coin.model.Coin;
import com.wade.crys.history.model.CoinHistory;

import java.util.List;

public interface CoinCollector {

    List<Coin> getCoinsFromAPI(boolean withLogoURL);
}
