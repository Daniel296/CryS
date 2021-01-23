package com.wade.crys.alert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wade.crys.alert.interfaces.AlertRepository;
import com.wade.crys.alert.interfaces.AlertService;
import com.wade.crys.alert.model.Alert;
import com.wade.crys.coin.interfaces.CoinService;
import com.wade.crys.coin.model.Coin;

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
    public void addAlert(Alert alert) {

        if(alert.getValue() > alert.getCoin().getPriceUsd()) {

            alert.setOperator(1);
        } else if(alert.getValue() < alert.getCoin().getPriceUsd()) {

            alert.setOperator(-1);
        } else {

            alert.setOperator(0);
        }

        alertRepository.addAlert(alert);
    }

    @Override
    public void deleteAlert(String id) {

        alertRepository.deleteAlertById(id);
    }
}
