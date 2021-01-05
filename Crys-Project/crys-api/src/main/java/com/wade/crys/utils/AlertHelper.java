package com.wade.crys.utils;

import com.wade.crys.coin.model.Coin;

public class AlertHelper {

    public boolean checkValueIsLowerOrEqual(Coin coin, Double value) {
        return value <= coin.getPriceUsd();
    }

    public boolean checkIfValueIsBiggerOrEqual(Coin coin, Double value) {
        return value >= coin.getPriceUsd();
    }

    public boolean checkIfValuesAreEqual(Coin coin, Double value) {
        return value.equals(coin.getPriceUsd());
    }
}
