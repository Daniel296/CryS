package com.wade.crys.favorite;

import com.wade.crys.coin.interfaces.CoinService;
import com.wade.crys.coin.model.Coin;
import com.wade.crys.favorite.model.FavoriteCoin;
import com.wade.crys.user.interfaces.UserService;
import com.wade.crys.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteCoinController {

    @Autowired
    private FavoriteCoinService favoriteCoinService;

    @GetMapping("/{user_id}")
    public ResponseEntity<List<Coin>> getFavoriteCoinsForUser(@PathVariable("user_id") String userId) {

        return new ResponseEntity<>(favoriteCoinService.getFavoriteCoinsForUser(userId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addFavorite(@RequestBody FavoriteCoin favoriteCoin) {

        try {

            favoriteCoinService.addFavoriteCoin(favoriteCoin);
        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity removeCoinFromFavorite(@RequestBody FavoriteCoin favoriteCoin) {

        try {

            favoriteCoinService.deleteFavoriteCoin(favoriteCoin);
        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
