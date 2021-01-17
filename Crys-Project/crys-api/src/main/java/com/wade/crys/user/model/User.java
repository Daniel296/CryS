package com.wade.crys.user.model;

import java.util.List;

import com.wade.crys.alert.model.Alert;
import com.wade.crys.coin.model.Coin;

public class User {

    private String uuid;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String telephone;

    private String token;

    private boolean emailNotification;

    private List<Coin> favoriteCoins;

    private List<Alert> alerts;

    public User() {}

    public User(String uuid, String firstName, String lastName, String email, String password, String telephone, boolean emailNotification, List<Coin> favoriteCoins, List<Alert> alerts) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.emailNotification = emailNotification;
        this.favoriteCoins = favoriteCoins;
        this.alerts = alerts;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(boolean emailNotification) {
        this.emailNotification = emailNotification;
    }

    public List<Coin> getFavoriteCoins() {
        return favoriteCoins;
    }

    public void setFavoriteCoins(List<Coin> favoriteCoins) {
        this.favoriteCoins = favoriteCoins;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
