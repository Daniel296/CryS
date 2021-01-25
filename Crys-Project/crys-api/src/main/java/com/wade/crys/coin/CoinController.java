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

        return new ResponseEntity<>(coinService.getAllCoinsOrderByRankAsc(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coin> getCoinById(@PathVariable String id) {

        Optional<Coin> optional = coinService.getCoinById(id);

        if(optional.isPresent()) {

            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}
