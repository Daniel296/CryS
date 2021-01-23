package com.wade.crys.history;

import static com.wade.crys.utils.rdf.CRYS.COIN_HISTORY_URI;
import static com.wade.crys.utils.rdf.CRYS.CRYS_URI;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.tdb.TDB;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wade.crys.history.interfaces.CoinHistoryRepository;
import com.wade.crys.history.model.CoinHistory;
import com.wade.crys.utils.rdf.CRYS;
import com.wade.crys.utils.rdf.CrysOntologyEnum;

@Repository
public class CoinHistoryRepositoryImpl implements CoinHistoryRepository {

    @Autowired
    private Dataset dataset;

    @Override
    public List<CoinHistory> getCoinHistory(String coinId) {

        List<CoinHistory> coinHistory = new ArrayList<>();

        dataset.begin(ReadWrite.READ);
        try{

            ResultSet rs = QueryExecutionFactory.create(String.format(CrysOntologyEnum.GET_HISTORY_FOR_COIN_QRY.getCode(), coinId), dataset).execSelect();

            while(rs.hasNext()) {

                QuerySolution qs = rs.next();

                String id = qs.get("history").toString().substring(qs.get("history").toString().indexOf("#") + 1)
                        .replace("coin-history-", "");
                Double priceUsd = Double.parseDouble(qs.get("priceUsd").toString());
                Long timestamp = Long.parseLong(qs.get("timestamp").toString());

                coinHistory.add(new CoinHistory(id, coinId, priceUsd, timestamp));
            }
        } catch (Exception e) {

            System.out.println(e);
        }
        finally {

            dataset.end();
        }

        return coinHistory;
    }

    @Override
    public void addCoinHistory(CoinHistory coinHistory) {

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
    public void deleteHistoryForCoin(String coinId) {

        dataset.begin(ReadWrite.WRITE);

        try {

            UpdateRequest req = UpdateFactory.create(String.format(CrysOntologyEnum.DELETE_HISTORY_FOR_COIN_QRY.getCode(), coinId));
            UpdateAction.execute(req, dataset);

            dataset.commit();
        } catch (Exception e) {

            dataset.abort();
            System.out.println(e);
        } finally {

            dataset.end();
            TDB.sync(dataset);
        }

    }

}
