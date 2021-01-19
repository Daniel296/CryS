package com.wade.crys.favorite;

import java.util.List;

import com.wade.crys.coin.model.Coin;

public interface FavoriteCoinRepository {

	List<Coin> getFavoriteCoinsForUser(String userId);

	void addFavoriteCoinToUser(String userId, String coinId);

	void deleteFavoriteCoinForUser(String userId, String coinId);
}
