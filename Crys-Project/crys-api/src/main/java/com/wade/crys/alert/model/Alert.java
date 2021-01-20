package com.wade.crys.alert.model;

import com.wade.crys.coin.model.Coin;
import com.wade.crys.user.model.User;

public class Alert {

    private String id;

    private Double value;

    private Integer operator;

    private String userId;

    private String coinId;

    public Alert() { }

    public Alert(String id, Double value, Integer operator, String userId, String coinId) {
        this.id = id;
        this.userId = userId;
        this.value = value;
        this.operator = operator;
        this.coinId = coinId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoin(String coinId) {
        this.coinId = coinId;
    }
}
