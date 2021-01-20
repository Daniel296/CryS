package com.wade.crys.alert;

import com.wade.crys.alert.interfaces.AlertRepository;
import com.wade.crys.alert.interfaces.AlertService;
import com.wade.crys.alert.model.Alert;
import com.wade.crys.user.model.User;
import com.wade.crys.utils.AlertHelper;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Override
    public List<Alert> getUserAlerts(String userId) {

        return alertRepository.getUserAlerts(userId);
    }

    @Override
    public void addAlert(Alert alert) {

        alertRepository.addAlert(alert);
    }

    @Override
    public void deleteAlert(String id) {

        alertRepository.deleteAlertByUserIdAndCoinId(id, "bitcoin");
    }
}
