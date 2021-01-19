package com.wade.crys.favorite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wade.crys.coin.model.Coin;
import com.wade.crys.favorite.model.FavoriteCoin;

@Service
public class FavoriteCoinServiceImpl implements FavoriteCoinService {

	@Autowired
	private FavoriteCoinRepository favoriteCoinRepository;

	@Override
	public List<Coin> getFavoriteCoinsForUser(String userId) {

		return favoriteCoinRepository.getFavoriteCoinsForUser(userId);
	}

	@Override
	public void addFavoriteCoin(FavoriteCoin favoriteCoin) {

		favoriteCoinRepository.addFavoriteCoinToUser(favoriteCoin.getUserId(), favoriteCoin.getCoinId());
	}

	@Override
	public void deleteFavoriteCoin(FavoriteCoin favoriteCoin) {

		favoriteCoinRepository.deleteFavoriteCoinForUser(favoriteCoin.getUserId(), favoriteCoin.getCoinId());
	}
}
