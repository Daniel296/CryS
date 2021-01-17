package com.wade.crys.history.model;

public class CoinHistory {

    private int id;

    private String coinId;

    private Double priceUSD;

    private Long timestamp;

    public CoinHistory(String coinId, Double priceUSD, Long timestamp) {
        this.coinId = coinId;
        this.priceUSD = priceUSD;
        this.timestamp = timestamp;
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
