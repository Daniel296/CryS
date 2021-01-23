package com.wade.crys.data.coin;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import com.wade.crys.coin.model.Coin;
import com.wade.crys.data.coin.interfaces.CoinCollector;

@Component
public class CoinCapAPI implements CoinCollector {

    private static final String IMAGE_NOT_FOUND_URL = "http://viegenpharma.com/wp-content/uploads/2019/01/NoImageFound.jpg.png";
    private static final String BASE_COIN_DATA__URL = "https://api.coincap.io/v2";
    private static final String BASE_COIN_LOGO__URL = "https://s2.coinmarketcap.com";

    @Override
    public List<Coin> getCoinsFromAPI(boolean withLogoURL) {
        String coinsDataAPIResponse = getResponseDataFromAPI(BASE_COIN_DATA__URL +  "/assets");

        List<Coin> coins = getCoinsFromResponseData(coinsDataAPIResponse);

        if(withLogoURL) {
            Map<String, String> logoUrls = getLogoUrlForCoins();

            for (Coin coin : coins) {

                coin.setLogoURL(logoUrls.get(coin.getName().toLowerCase()));
            }
        }

        return coins;
    }
    private Map<String, String> getLogoUrlForCoins() {
        // {"name": "Bitcoin", "symbol": "BTC", "rank": 1, "slug": "bitcoin", "tokens": ["Bitcoin", "bitcoin", "BTC"], "id": 1}
        String logoURLForCoinsResponseAPI = getResponseDataFromAPI(BASE_COIN_LOGO__URL + "/generated/search/quick_search.json");

        return getLogoUrlFromResponseData(logoURLForCoinsResponseAPI);
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

    private List<Coin> getCoinsFromResponseData(String responseData) {
        List<Coin> coins = new ArrayList<>();

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try
        {
            //Read JSON file
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseData);
            JSONArray data = (JSONArray) jsonObject.get("data");

            for(Object coinData : data) {
                JSONObject jsonCoin = (JSONObject) coinData;
                String id = (String) jsonCoin.get("id");
                Integer rank = Integer.parseInt((String) jsonCoin.get("rank"));
                String symbol = (String) jsonCoin.get("symbol");
                String name = (String) jsonCoin.get("name");
                Double supply = Double.parseDouble((String) jsonCoin.get("supply"));
                Double maxSupply =  jsonCoin.get("maxSupply") == null ? 0.0 : Double.parseDouble((String) jsonCoin.get("maxSupply"));
                Double marketCapUsd =  Double.parseDouble((String) jsonCoin.get("marketCapUsd"));
                Double volumeUsd24Hr =  Double.parseDouble((String) jsonCoin.get("volumeUsd24Hr"));
                Double priceUsd =  Double.parseDouble((String) jsonCoin.get("priceUsd"));
                Double changePercent24Hr =  Double.parseDouble((String) jsonCoin.get("changePercent24Hr"));
                Double vwap24Hr =  jsonCoin.get("vwap24Hr") == null ? 0.0 : Double.parseDouble((String) jsonCoin.get("vwap24Hr"));

                coins.add(new Coin(id, name, rank, symbol, IMAGE_NOT_FOUND_URL, supply, maxSupply, marketCapUsd, volumeUsd24Hr, priceUsd, changePercent24Hr, vwap24Hr));
            }

        } catch (ParseException ignored) { }

        return coins;
    }

    private static Map<String, String> getLogoUrlFromResponseData(String responseData) {
        Map<String, String> logoUrls = new HashMap<>();

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try
        {
            JSONArray data = (JSONArray) jsonParser.parse(responseData);

            for(Object coinData : data) {
                JSONObject jsonCoin = (JSONObject) coinData;
                Long id = (Long) jsonCoin.get("id");
                String name = (String) jsonCoin.get("name");

                logoUrls.put(name.toLowerCase(), BASE_COIN_LOGO__URL + "/static/img/coins/32x32/" + id + ".png");
            }

        } catch (ParseException ignored) { }

        return logoUrls;
    }
}
