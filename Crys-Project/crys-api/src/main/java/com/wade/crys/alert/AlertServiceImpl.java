package com.wade.crys.alert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wade.crys.alert.email.AlertEmailSender;
import com.wade.crys.alert.interfaces.AlertRepository;
import com.wade.crys.alert.interfaces.AlertService;
import com.wade.crys.alert.model.Alert;
import com.wade.crys.coin.interfaces.CoinService;
import com.wade.crys.coin.model.Coin;
import com.wade.crys.utils.AlertHelper;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private CoinService coinService;

    @Override
    public List<Alert> getUserAlerts(String userId) {

        List<Alert> alerts = alertRepository.getUserAlerts(userId);

        alerts.forEach(alert -> alert.setCoin(coinService.getCoinById(alert.getCoin().getId()).get()));

        return alerts;
    }

    @Override
    public List<Alert> getUserAlertsThatShouldBeTriggered(String userId) {

        List<Alert> userAlerts = getUserAlerts(userId);

        int i = 0;
        while(i < userAlerts.size()) {

            if(AlertHelper.alertShouldBeTriggered(userAlerts.get(i))) {

               i++;
            } else {

                userAlerts.remove(i);
            }
        }

        return userAlerts;
    }

    @Override
    public void addAlert(Alert alert) {

       alert.setOperator(AlertHelper.getOperator(alert.getValue(), alert.getCoin().getPriceUsd()));

        alertRepository.addAlert(alert);
    }

    @Override
    public void deleteAlert(String id) {

        alertRepository.deleteAlertById(id);
    }

}
