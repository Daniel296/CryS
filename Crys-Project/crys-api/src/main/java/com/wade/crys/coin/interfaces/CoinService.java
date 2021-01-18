package com.wade.crys.coin.interfaces;

import com.wade.crys.coin.model.Coin;
import com.wade.crys.history.model.CoinHistory;

import java.util.List;
import java.util.Optional;

public interface CoinService {

    Optional<Coin> getCoinById(String id);

    List<Coin> getCoinsBySearchTerm(String term);

    List<Coin> getAllCoinsOrderByRankAsc();

    void addCoin(Coin coin);

    void updateCoin(Coin coin);
}
