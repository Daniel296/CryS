package com.wade.crys.alert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wade.crys.alert.interfaces.AlertRepository;
import com.wade.crys.alert.interfaces.AlertService;
import com.wade.crys.alert.model.Alert;

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
