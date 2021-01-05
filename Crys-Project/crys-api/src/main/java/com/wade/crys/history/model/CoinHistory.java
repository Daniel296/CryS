package com.wade.crys.history.model;

public class CoinHistory {

    private int id;

    private String coinId;

    private Double priceUSD;

    private Long time;

    public CoinHistory(String coinId, Double priceUSD, Long time) {
        this.coinId = coinId;
        this.priceUSD = priceUSD;
        this.time = time;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(Double priceUSD) {
        this.priceUSD = priceUSD;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
