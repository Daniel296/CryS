package com.wade.crys.history.interfaces;

import com.wade.crys.history.model.CoinHistory;

import java.util.List;

public interface CoinHistoryService {

    List<CoinHistory> getCoinHistory(String id);

}
