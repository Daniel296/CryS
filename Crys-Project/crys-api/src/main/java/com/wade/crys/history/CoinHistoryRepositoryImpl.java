package com.wade.crys.history;

import com.wade.crys.history.interfaces.CoinHistoryRepository;
import com.wade.crys.history.model.CoinHistory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CoinHistoryRepositoryImpl implements CoinHistoryRepository {

    private Map<String, List<CoinHistory>> coinsHistories = new HashMap<>();

    @Override
    public List<CoinHistory> getCoinHistory(String id) {
        return coinsHistories.get(id);
    }

}
