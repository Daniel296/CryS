package com.wade.crys.alert.interfaces;

import com.wade.crys.alert.model.Alert;
import com.wade.crys.user.model.User;

import java.util.List;
import java.util.Optional;

public interface AlertService {

    List<Alert> getUserAlerts(String userId);

    List<Alert> getUserAlertsThatShouldBeTriggered(String userId);

    void addAlert(Alert alert);

    void deleteAlert(String id);

}
