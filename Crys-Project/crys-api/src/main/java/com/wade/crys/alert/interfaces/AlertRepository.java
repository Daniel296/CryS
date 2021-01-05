package com.wade.crys.alert.interfaces;

import com.wade.crys.alert.model.Alert;

import java.util.List;
import java.util.Optional;

public interface AlertRepository {

    List<Alert> getUserAlerts(String userId);

    List<Alert> getAllAlertsByUser(String userId);

    Optional<Alert> getAlertById(String id);

    void addAlert(Alert alert);

    void deleteAlert(String id);

    void updateAlert(String alertId, Double newAlertValue);

}
