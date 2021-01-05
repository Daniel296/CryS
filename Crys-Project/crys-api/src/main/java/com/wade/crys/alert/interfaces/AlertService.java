package com.wade.crys.alert.interfaces;

import com.wade.crys.alert.model.Alert;
import com.wade.crys.user.model.User;

import java.util.List;
import java.util.Optional;

public interface AlertService {

    List<Alert> getUserAlerts(User user);

    List<Alert> fireAlertsForUser(User user);

    Optional<Alert> getAlertById(String id);

    void addAlert(Alert alert);

    void deleteAlert(String id);

    void updateAlert(String alertId, Double newAlertValue);


}
