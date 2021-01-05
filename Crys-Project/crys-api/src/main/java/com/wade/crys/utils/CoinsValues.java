package com.wade.crys.utils;

import com.wade.crys.coin.model.Coin;

public class CoinsValues {

    public static Coin coin1;
    public static Coin coin2;
    public static Coin coin3;

    static {
        coin1 = new Coin("bitcoin", "Bitcoin", 1, "BTC", "no-contesa", 31312D, 123123D, 312312D, 12312D, 100D,12312D, 123123D);
        coin2 = new Coin("litecoin", "Litecoin", 6, "LTC", "no-contesa", 31312D, 123123D, 312312D, 12312D, 50D,12312D, 123123D);
        coin3 = new Coin("ethereum", "Ethereum", 2, "ETH", "no-contesa", 31312D, 123123D, 312312D, 12312D, 200D,12312D, 123123D);
    }
}
