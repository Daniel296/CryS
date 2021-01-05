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
    public List<Coin> getAllCoins() {
        return coinRepository.getAllCoins();
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
    public void updateCoin(String id, Coin updatedCoin) {

        Optional<Coin> optional = coinRepository.getCoinById(id);

        if(optional.isPresent()) {
            Coin coin = optional.get();

            coin.setName(updatedCoin.getName());
            coin.setLogoURL(updatedCoin.getLogoURL());
            coin.setVolumeUsd24hr(updatedCoin.getVolumeUsd24hr());
            coin.setMaxSupply(updatedCoin.getMaxSupply());
            coin.setChangePercentage24hr(updatedCoin.getChangePercentage24hr());
            coin.setPriceUsd(updatedCoin.getPriceUsd());
            coin.setRank(updatedCoin.getRank());
            coin.setSupply(updatedCoin.getSupply());
            coin.setSymbol(updatedCoin.getSymbol());
            coin.setVwap24hr(updatedCoin.getVwap24hr());

            coinRepository.addCoin(coin);
        }
    }

    @Override
    public void deleteAllCoins() {
        coinRepository.deleteAll();
    }


}
