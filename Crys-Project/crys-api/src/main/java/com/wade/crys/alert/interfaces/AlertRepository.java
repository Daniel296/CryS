package com.wade.crys.alert.interfaces;

import com.wade.crys.alert.model.Alert;

import java.util.List;
import java.util.Optional;

public interface AlertRepository {

    List<Alert> getUserAlerts(String userId);

    void addAlert(Alert alert);

    void deleteAlertById(String id);

    void deleteAlertByUserIdAndCoinId(String userId, String coinId);


}
