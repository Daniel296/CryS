package com.wade.crys.coin;

import static com.wade.crys.utils.CoinsValues.coin1;
import static com.wade.crys.utils.CoinsValues.coin2;
import static com.wade.crys.utils.CoinsValues.coin3;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import com.wade.crys.coin.interfaces.CoinRepository;
import com.wade.crys.coin.model.Coin;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CoinRepositoryImpl implements CoinRepository {

    private List<Coin> coins = new ArrayList<>(asList(coin1, coin2, coin3));

    @Override
    public Optional<Coin> getCoinById(String id) {
        return coins.stream().filter(coin -> coin.getId().equals(id)).findFirst();
    }

    @Override
    public List<Coin> getAllCoins() {
        return coins;
    }

    @Override
    public List<Coin> getAllCoinsOrderByRankAsc() {
        return coins.stream().sorted(new Comparator<Coin>() {
            @Override
            public int compare(Coin coin1, Coin coin2) {
                if(coin1.getRank() < coin2.getRank()) {
                    return -1;
                }

                if(coin1.getRank() > coin2.getRank()) {
                    return 1;
                }

                return 0;
            }
        }).collect(toList());
    }

    @Override
    public List<Coin> getAllByNameContaining(String term) {
        return coins.stream().filter(coin -> coin.getName().contains(term)).collect(toList());
    }

    @Override
    public void addCoin(Coin coin) {
        coins.add(coin);
    }

    @Override
    public void updateCoin(String id, Coin coin) {
        for(Coin coin1 : coins) {
            if(coin1.getId().equals(id)) {
                coin1.setPriceUsd(coin.getPriceUsd());
                coin1.setChangePercentage24hr(coin.getChangePercentage24hr());
                coin1.setMarketCapUsd(coin.getMarketCapUsd());
                coin1.setMaxSupply(coin.getMaxSupply());
                coin1.setVolumeUsd24hr(coin.getVolumeUsd24hr());
            }
        }
    }

    @Override
    public void deleteAll() {
        this.coins = new ArrayList<>();
    }
}
