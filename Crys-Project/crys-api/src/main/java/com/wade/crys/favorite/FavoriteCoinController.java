package com.wade.crys.favorite;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wade.crys.coin.model.Coin;
import com.wade.crys.favorite.model.FavoriteCoin;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteCoinController {

    @Autowired
    private FavoriteCoinService favoriteCoinService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Coin>> getFavoriteCoinsForUser(@PathVariable("userId") String userId) {

        List<Coin> favoriteCoins = new ArrayList<>();

        try {

            favoriteCoins = favoriteCoinService.getFavoriteCoinsForUser(userId);
        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(favoriteCoins, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addFavorite(@RequestBody FavoriteCoin favoriteCoin) {

        favoriteCoinService.addFavoriteCoin(favoriteCoin);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/remove")
    public ResponseEntity removeCoinFromFavorite(@RequestBody FavoriteCoin favoriteCoin) {

        favoriteCoinService.deleteFavoriteCoin(favoriteCoin);

        return new ResponseEntity(HttpStatus.OK);
    }
}
