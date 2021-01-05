package com.wade.crys.alert.model;

import com.wade.crys.coin.model.Coin;
import com.wade.crys.user.model.User;

public class Alert {

    private String id;

    private Double alertValue;

    private Integer operator;

    private User user;

    private Coin coin;

    public Alert() { }

    public Alert(String id, Double alertValue, Integer operator, User user, Coin coin) {
        this.id = id;
        this.user = user;
        this.alertValue = alertValue;
        this.operator = operator;
        this.coin = coin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    public Double getAlertValue() {
        return alertValue;
    }

    public void setAlertValue(Double alertValue) {
        this.alertValue = alertValue;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }
}
