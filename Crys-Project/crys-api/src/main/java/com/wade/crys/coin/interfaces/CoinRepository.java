package com.wade.crys.coin.interfaces;

import com.wade.crys.coin.model.Coin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository {

    Optional<Coin> getCoinById(String id);

    List<Coin> getAllCoinsOrderByRankAsc();

    List<Coin> getAllByNameContaining(String term);

    void addCoin(Coin coin);
}
