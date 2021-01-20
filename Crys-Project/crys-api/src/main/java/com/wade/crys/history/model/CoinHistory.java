package com.wade.crys.history.model;

public class CoinHistory {

    private String id;

    private String coinId;

    private Double priceUSD;

    private Long timestamp;

    public CoinHistory(String id, String coinId, Double priceUSD, Long timestamp) {

        this.id = id;
        this.coinId = coinId;
        this.priceUSD = priceUSD;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
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
