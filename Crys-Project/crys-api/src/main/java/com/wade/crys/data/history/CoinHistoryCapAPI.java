package com.wade.crys.data.history;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.wade.crys.coin.model.Coin;
import com.wade.crys.data.coin.interfaces.CoinCollector;
import com.wade.crys.data.history.interfaces.CoinHistoryCollector;
import com.wade.crys.history.model.CoinHistory;

@Component
public class CoinHistoryCapAPI implements CoinHistoryCollector {

    private static final String BASE_COIN_HISTORY__URL = "https://api.coincap.io/v2";

    @Override
    public List<CoinHistory> getCoinsHistoryFromAPI(String coinId) {
        String coinHistoryAPIResponse = getResponseDataFromAPI(BASE_COIN_HISTORY__URL +  "/assets/" + coinId + "/history?interval=d1");

        return getCoinHistory(coinHistoryAPIResponse, coinId);
    }

    private static String getResponseDataFromAPI(String apiUrl) {
        StringBuilder responseData = new StringBuilder();

        try {
            URL url = new URL(apiUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            conn.setRequestProperty("Accept-Encoding", "deflate");
            conn.setRequestMethod("GET");
            conn.connect();

            if(conn.getResponseCode() != 200) {
                throw new RuntimeException("HttpResponseCode: " + conn.getResponseCode());
            }
            else {
                Scanner sc = new Scanner(conn.getInputStream());
                while(sc.hasNext()){
                    responseData.append(sc.nextLine());
                }
                sc.close();
            }

        } catch (IOException ignored) { }

        return responseData.toString();
    }


    private static List<CoinHistory> getCoinHistory(String responseData, String coinId) {
        List<CoinHistory> coinHistories = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();

        try
        {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseData);
            JSONArray data = (JSONArray) jsonObject.get("data");

            for(Object historyData : data) {

                JSONObject jsonCoin = (JSONObject) historyData;
                String priceUSD = (String) jsonCoin.get("priceUsd");
                Long time = (Long) jsonCoin.get("time");

               coinHistories.add(new CoinHistory(UUID.randomUUID().toString(), coinId, Double.parseDouble(priceUSD), time));
            }

        } catch (ParseException ignored) {}

        return coinHistories;
    }
}
