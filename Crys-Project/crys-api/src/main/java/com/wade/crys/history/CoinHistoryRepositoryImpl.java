package com.wade.crys.history;

import static com.wade.crys.utils.rdf.CRYS.ALERT_URI;
import static com.wade.crys.utils.rdf.CRYS.COIN_HISTORY_URI;
import static com.wade.crys.utils.rdf.CRYS.CRYS_URI;

import com.wade.crys.history.interfaces.CoinHistoryRepository;
import com.wade.crys.history.model.CoinHistory;
import com.wade.crys.utils.rdf.CRYS;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.tdb.TDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CoinHistoryRepositoryImpl implements CoinHistoryRepository {

    @Autowired
    private Dataset dataset;

    @Override
    public List<CoinHistory> getCoinHistory(String coinId) {

        return null;
    }

    @Override
    public void addCoinHistory(CoinHistory coinHistory) {

        // trebuie facut delete insert -> update
        // avem aceleasi date
        dataset.begin(ReadWrite.WRITE);

        try {
            Model coinModel = dataset.getDefaultModel();

            Resource coinResource = coinModel.createResource(CRYS_URI + "coin-history-" + coinHistory.getId());
            coinResource.addProperty(CRYS.type, coinModel.createResource(CRYS_URI + "CoinHistory"));
            coinResource.addProperty(CRYS.belongsTo, coinHistory.getCoinId());
            coinResource.addProperty(CRYS.priceUsd, coinHistory.getPriceUSD().toString());
            coinResource.addProperty(CRYS.timestamp, coinHistory.getTimestamp().toString());

            dataset.addNamedModel(COIN_HISTORY_URI + coinHistory.getId(), coinModel);

            dataset.commit();
        } catch (Exception e) {

            dataset.abort();
            System.out.println(e);
        } finally {

            dataset.end();
            TDB.sync(dataset);
        }
    }

    @Override
    public void deleteAllCoinHistory() {

        // trebuie facut delete insert -> update
        // avem aceleasi date
    }

}
