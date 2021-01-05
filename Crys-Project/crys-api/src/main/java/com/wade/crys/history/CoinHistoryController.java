package com.wade.crys.history;

import com.wade.crys.history.interfaces.CoinHistoryService;
import com.wade.crys.history.model.CoinHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coin")
public class CoinHistoryController {

    @Autowired
    private CoinHistoryService coinHistoryService;

    @GetMapping("/{id}/history")
    public ResponseEntity getCoinHistory(@PathVariable String id) {
        List<CoinHistory> coinHistories = coinHistoryService.getCoinHistory(id);

        if(coinHistories != null) {
            return new ResponseEntity<>(coinHistories, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
