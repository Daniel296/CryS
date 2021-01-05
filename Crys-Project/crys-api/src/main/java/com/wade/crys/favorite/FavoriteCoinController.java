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
    private UserService userService;

    @Autowired
    private CoinService coinService;

    @GetMapping("/{user_id}")
    public ResponseEntity<List<Coin>> getFavoriteCoinsForUser(@PathVariable("user_id") String userId) {
        Optional<User> optional = userService.getUserById(userId);

        if(!optional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = optional.get();

        return new ResponseEntity<>(user.getFavoriteCoins(), HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity addFavorite(@RequestBody FavoriteCoin favoriteCoin) {
        Optional<User> optionalUser = userService.getUserById(favoriteCoin.getUserId());
        Optional<Coin> optionalCoin = coinService.getCoinById(favoriteCoin.getCoinId());

        if(!optionalUser.isPresent() || !optionalCoin.isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();
        Coin coin = optionalCoin.get();

        user.getFavoriteCoins().add(coin);

        userService.updateUser(user.getUuid(), user);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/remove")
    public ResponseEntity removeCoinFromFavorite(@RequestBody FavoriteCoin favoriteCoin) {
        Optional<Coin> optionalCoin = coinService.getCoinById(favoriteCoin.getCoinId());
        Optional<User> optionalUser = userService.getUserById(favoriteCoin.getUserId());

        if(!optionalUser.isPresent() || !optionalCoin.isPresent()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();
        Coin coin = optionalCoin.get();

        user.getFavoriteCoins().remove(coin);

        userService.updateUser(user.getUuid(), user);

        return new ResponseEntity(HttpStatus.OK);
    }
}
