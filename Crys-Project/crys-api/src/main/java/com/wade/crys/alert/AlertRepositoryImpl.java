package com.wade.crys.alert;

import static com.wade.crys.utils.rdf.CRYS.ALERT_URI;
import static com.wade.crys.utils.rdf.CRYS.CRYS_URI;

import com.wade.crys.alert.interfaces.AlertRepository;
import com.wade.crys.alert.model.Alert;
import com.wade.crys.coin.model.Coin;
import com.wade.crys.utils.rdf.CRYS;
import com.wade.crys.utils.rdf.CrysOntologyEnum;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.tdb.TDB;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlertRepositoryImpl implements AlertRepository {

    @Autowired
    private Dataset dataset;

    @Override
    public List<Alert> getUserAlerts(String userId) {

        List<Alert> alerts = new ArrayList<>();

        dataset.begin(ReadWrite.READ);
        try{

            ResultSet rs = QueryExecutionFactory.create(String.format(CrysOntologyEnum.GET_USER_ALERTS_QRY.getCode(), userId), dataset).execSelect();

            while(rs.hasNext()) {

                QuerySolution qs = rs.next();

                String id = qs.get("alert").toString().substring(qs.get("alert").toString().indexOf("-") + 1);
                Double value = Double.parseDouble(qs.get("value").toString());
                Integer operator = Integer.parseInt(qs.get("operator").toString());
                String coinId = qs.get("forCoin").toString();

                alerts.add(new Alert(id, value, operator, userId, new Coin(coinId)));
            }
        } catch (Exception e) {

            System.out.println(e);
        }
        finally {

            dataset.end();
        }

        return alerts;
    }

    @Override
    public void addAlert(Alert alert) {

        dataset.begin(ReadWrite.WRITE);

        try {
            Model coinModel = dataset.getDefaultModel();

            Resource coinResource = coinModel.createResource(CRYS_URI + alert.getId());
            coinResource.addProperty(CRYS.type, coinModel.createResource(CRYS_URI + "Alert"));
            coinResource.addProperty(CRYS.belongsTo, alert.getUserId());
            coinResource.addProperty(CRYS.forCoin, alert.getCoin().getId());
            coinResource.addProperty(CRYS.value, alert.getValue().toString());
            coinResource.addProperty(CRYS.operator, alert.getOperator().toString());

            dataset.addNamedModel(ALERT_URI + alert.getId(), coinModel);

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
    public void deleteAlertById(String id) {

        execUpdateOrDeleteStatement(String.format(CrysOntologyEnum.DELETE_ALERT_BY_ID_QRY.getCode(), id, id));
    }

    @Override
    public void deleteAlertByUserIdAndCoinId(String userId, String coinId) {

        execUpdateOrDeleteStatement(String.format(CrysOntologyEnum.DELETE_ALERT_BY_USER_ID_AND_COIN_ID_QRY.getCode(), userId, coinId, userId, coinId));
    }

    private void execUpdateOrDeleteStatement(String statement) {

        dataset.begin(ReadWrite.WRITE);

        try {

            UpdateRequest req = UpdateFactory.create(statement) ;
            UpdateAction.execute(req, dataset);

            dataset.commit();
        } catch (Exception e) {

            System.out.println(e);
        } finally {

            dataset.end();
            TDB.sync(dataset);
        }
    }
}
