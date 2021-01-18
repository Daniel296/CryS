package com.wade.crys.coin;

import com.wade.crys.coin.interfaces.CoinRepository;
import com.wade.crys.coin.interfaces.CoinService;
import com.wade.crys.coin.model.Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoinServiceImpl implements CoinService {

    @Autowired
    private CoinRepository coinRepository;

    @Override
    public Optional<Coin> getCoinById(String id) {

        return coinRepository.getCoinById(id);
    }

    @Override
    public List<Coin> getCoinsBySearchTerm(String term) {

        return coinRepository.getAllByNameContaining(term);
    }

    @Override
    public List<Coin> getAllCoinsOrderByRankAsc() {

        return coinRepository.getAllCoinsOrderByRankAsc();
    }

    @Override
    public void addCoin(Coin coin) {

        coinRepository.addCoin(coin);
    }

    @Override
    public void updateCoin(Coin coin) {

        coinRepository.updateCoin(coin);
    }
}
