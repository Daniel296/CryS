package com.wade.crys.favorite;

import java.util.List;

import com.wade.crys.coin.model.Coin;
import com.wade.crys.favorite.model.FavoriteCoin;

public interface FavoriteCoinService {

	List<Coin> getFavoriteCoinsForUser(String userId);

	void addFavoriteCoin(FavoriteCoin favoriteCoin);

	void deleteFavoriteCoin(FavoriteCoin favoriteCoin);

}
