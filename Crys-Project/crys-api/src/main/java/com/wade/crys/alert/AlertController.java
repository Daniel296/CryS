package com.wade.crys.alert;

import com.wade.crys.alert.interfaces.AlertService;
import com.wade.crys.alert.model.Alert;
import com.wade.crys.user.interfaces.UserService;
import com.wade.crys.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Alert>> getUserAlerts(@PathVariable("userId") String userId) {

        return new ResponseEntity<>(alertService.getUserAlerts(userId), HttpStatus.OK);
    }

    @GetMapping("/trigger/{userId}")
    public ResponseEntity<List<Alert>> triggerUserAlerts(@PathVariable("userId") String userId) {

        return new ResponseEntity<>(alertService.getUserAlertsThatShouldBeTriggered(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addAlert(@RequestBody Alert alert) {

        try {

            alert.setId("alert-" + UUID.randomUUID());

            alertService.addAlert(alert);

            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity deleteAlert(@PathVariable String id) {

        try {

            alertService.deleteAlert(id);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
