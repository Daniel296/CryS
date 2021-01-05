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

    @Autowired
    private KieContainer kieContainer;

    @Override
    public List<Alert> getUserAlerts(User user) {
        return alertRepository.getAllAlertsByUser(user.getUuid());
    }

    @Override
    public List<Alert> fireAlertsForUser(User user) {
        // fire alerts with drools
        List<Alert> alerts = new ArrayList<>();
        List<Alert> userAlerts = alertRepository.getAllAlertsByUser(user.getUuid());

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("alerts", alerts);
        kieSession.setGlobal("helper", new AlertHelper());

        for (Alert alert : userAlerts) {
            kieSession.insert(alert);
        }

        int fired = kieSession.fireAllRules();
        System.out.println("Fired rules: " + fired);

        kieSession.destroy();
        return alerts;
    }

    @Override
    public Optional<Alert> getAlertById(String id) {
        return alertRepository.getAlertById(id);
    }

    @Override
    public void addAlert(Alert alert) {
        alertRepository.addAlert(alert);
    }

    @Override
    public void deleteAlert(String id) {

    }

    @Override
    public void updateAlert(String alertId, Double newAlertValue) {

    }
}
