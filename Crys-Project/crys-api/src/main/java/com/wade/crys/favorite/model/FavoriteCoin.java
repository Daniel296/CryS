package com.wade.crys.favorite.model;

public class FavoriteCoin {

    private String userId;
    private String coinId;

    public FavoriteCoin() {
    }

    public FavoriteCoin(String userId, String coinId) {
        this.userId = userId;
        this.coinId = coinId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }
}
