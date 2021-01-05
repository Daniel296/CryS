package com.wade.crys.history;

import com.wade.crys.history.interfaces.CoinHistoryRepository;
import com.wade.crys.history.interfaces.CoinHistoryService;
import com.wade.crys.history.model.CoinHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinHistoryServiceImp implements CoinHistoryService {

    @Autowired
    private CoinHistoryRepository coinHistoryRepository;

    @Override
    public List<CoinHistory> getCoinHistory(String id) {
        return coinHistoryRepository.getCoinHistory(id);
    }

}
