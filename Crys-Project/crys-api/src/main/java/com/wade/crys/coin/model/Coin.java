package com.wade.crys.coin.model;

public class Coin {

    private String id;

    private String name;

    private Integer rank;

    private String symbol;

    private String logoURL;

    private Double supply;

    private Double maxSupply;

    private Double marketCapUsd;

    private Double volumeUsd24hr;

    private Double priceUsd;

    private Double changePercentage24hr;

    private Double vwap24hr;

    public Coin() { }

    public Coin(String coinId) {

        this.id = coinId;
    }

    public Coin(String id, String name, Integer rank, String symbol, String logoURL, Double supply, Double maxSupply, Double marketCapUsd, Double volumeUsd24hr, Double priceUsd, Double changePercentage24hr, Double vwap24hr) {

        this.id = id;
        this.name = name;
        this.rank = rank;
        this.symbol = symbol;
        this.logoURL = logoURL;
        this.supply = supply;
        this.maxSupply = maxSupply;
        this.marketCapUsd = marketCapUsd;
        this.volumeUsd24hr = volumeUsd24hr;
        this.priceUsd = priceUsd;
        this.changePercentage24hr = changePercentage24hr;
        this.vwap24hr = vwap24hr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public Double getSupply() {
        return supply;
    }

    public void setSupply(Double supply) {
        this.supply = supply;
    }

    public Double getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(Double maxSupply) {
        this.maxSupply = maxSupply;
    }

    public Double getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(Double marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public Double getVolumeUsd24hr() {
        return volumeUsd24hr;
    }

    public void setVolumeUsd24hr(Double volumeUsd24hr) {
        this.volumeUsd24hr = volumeUsd24hr;
    }

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Double getChangePercentage24hr() {
        return changePercentage24hr;
    }

    public void setChangePercentage24hr(Double changePercentage24hr) {
        this.changePercentage24hr = changePercentage24hr;
    }

    public Double getVwap24hr() {
        return vwap24hr;
    }

    public void setVwap24hr(Double vwap24hr) {
        this.vwap24hr = vwap24hr;
    }

}
