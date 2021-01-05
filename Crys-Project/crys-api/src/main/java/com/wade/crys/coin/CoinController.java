package com.wade.crys.coin;

import com.wade.crys.coin.interfaces.CoinService;
import com.wade.crys.coin.model.Coin;
import com.wade.crys.history.model.CoinHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coin")
public class CoinController {

    @Autowired
    private CoinService coinService;

    @GetMapping("")
    public ResponseEntity<List<Coin>> getAllCoins() {
        List<Coin> coins;

        try {
            coins = coinService.getAllCoinsOrderByRankAsc();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(coins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coin> getCoinById(@PathVariable String id) {
        Optional<Coin> optional = coinService.getCoinById(id);

        if(optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/search/{term}")
    public ResponseEntity<List<Coin>> getCoinsBySearchTerm(@PathVariable String term) {
        return new ResponseEntity<>(coinService.getCoinsBySearchTerm(term), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity addCoin(@RequestBody Coin coin) {

        try {
            coinService.addCoin(coin);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
