package com.wade.crys.alert;

import com.wade.crys.alert.interfaces.AlertRepository;
import com.wade.crys.alert.model.Alert;
import com.wade.crys.coin.model.Coin;
import com.wade.crys.user.model.User;
import com.wade.crys.utils.CoinsValues;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AlertRepositoryImpl implements AlertRepository {

    private List<Alert> alerts = Collections.emptyList();

//    private List<Alert> alerts = Arrays.asList(new Alert("1", 12d, 90, new User(), CoinsValues.coin1),
//            new Alert("2", "13", 138.80, -1, CoinsValues.coin3),
//            new Alert("3", "12", 43D, 1, CoinsValues.coin2));


    @Override
    public List<Alert> getUserAlerts(String userId) {
        return this.alerts.stream()
                .filter(alert -> alert.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Alert> getAllAlertsByUser(String userId) {
        return null;
    }

    @Override
    public Optional<Alert> getAlertById(String id) {
        return alerts.stream()
                .filter(alert -> alert.getId().equals(id))
                .findFirst();
    }

    @Override
    public void addAlert(Alert alert) {
        this.alerts.add(alert);
    }

    @Override
    public void deleteAlert(String id) {

    }

    @Override
    public void updateAlert(String alertId, Double newAlertValue) {

    }
}
