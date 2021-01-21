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

        try {

            List<CoinHistory> coinHistory = coinHistoryService.getCoinHistory(id);

            return new ResponseEntity<>(coinHistory, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
