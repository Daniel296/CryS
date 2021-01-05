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

@RestController
@RequestMapping("/api/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @Autowired
    private UserService userService;

    @GetMapping("/{user_id}")
    public ResponseEntity<List<Alert>> getUserAlerts(@PathVariable("user_id") String userId) {
        Optional<User> optional = userService.getUserById(userId);

        if(!optional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(alertService.getUserAlerts(optional.get()), HttpStatus.OK);
    }

    @GetMapping("fire/{user_id}")
    public ResponseEntity<List<Alert>> fireAlerts(@PathVariable("user_id") String userId) {
        Optional<User> optional = userService.getUserById(userId);

        if(!optional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(alertService.fireAlertsForUser(optional.get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addAlert(@RequestBody Alert alert) {
        try {
            alertService.addAlert(alert);

            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAlert(@PathVariable String id, @RequestBody Double newAlertValue ) {
        try {
            alertService.updateAlert(id, newAlertValue);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
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
