package com.wade.crys.coin;

import static com.wade.crys.utils.CoinsValues.coin1;
import static com.wade.crys.utils.CoinsValues.coin2;
import static com.wade.crys.utils.CoinsValues.coin3;
import static com.wade.crys.utils.rdf.CRYS.COIN_URI;
import static com.wade.crys.utils.rdf.CRYS.CRYS_URI;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import com.wade.crys.coin.interfaces.CoinRepository;
import com.wade.crys.coin.model.Coin;
import com.wade.crys.user.model.User;
import com.wade.crys.utils.rdf.CRYS;
import com.wade.crys.utils.rdf.CrysOntologyEnum;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class CoinRepositoryImpl implements CoinRepository {

    @Autowired
    private Dataset dataset;

    private List<Coin> coins = new ArrayList<>(asList(coin1, coin2, coin3));

    @Override
    public Optional<Coin> getCoinById(String id) {
        return coins.stream().filter(coin -> coin.getId().equals(id)).findFirst();
    }

    @Override
    public List<Coin> getAllCoinsOrderByRankAsc() {

        List<Coin> coins = new ArrayList<>();

        dataset.begin(ReadWrite.READ);

        try{
            ResultSet rs = QueryExecutionFactory.create(CrysOntologyEnum.GET_ALL_COINS_ORDERED_BY_RANK_QRY.getCode(), dataset).execSelect();

            while(rs.hasNext()) {

                QuerySolution qs = rs.next();

                String name = qs.get("name").toString();
                Integer rank = Integer.parseInt(qs.get("rank").toString());
                String symbol = qs.get("symbol").toString();
                String logoUrl = qs.get("logoUrl").toString();
                Double supply = Double.parseDouble(qs.get("supply").toString());
                Double maxSupply = Double.parseDouble(qs.get("maxSupply").toString());
                Double marketCapUsd = Double.parseDouble(qs.get("marketCapUsd").toString());
                Double volumeUsd24hr = Double.parseDouble(qs.get("volumeUsd24hr").toString());
                Double priceUsd = Double.parseDouble(qs.get("priceUsd").toString());
                Double changePercentage24hr = Double.parseDouble(qs.get("changePercentage24hr").toString());
                Double vwap24hr = Double.parseDouble(qs.get("vwap24hr").toString());

                coins.add(new Coin(name, name, rank, symbol, logoUrl, supply, maxSupply, marketCapUsd, volumeUsd24hr, priceUsd, changePercentage24hr, vwap24hr));
            }

        } finally {

            dataset.end();
        }

        return coins;
    }

    @Override
    public List<Coin> getAllByNameContaining(String term) {
        return coins.stream().filter(coin -> coin.getName().contains(term)).collect(toList());
    }

    @Override
    public synchronized void addCoin(Coin coin) {

        dataset.begin(ReadWrite.WRITE);

        Model coinModel = dataset.getDefaultModel();

        Resource coinResource = coinModel.createResource(CRYS_URI + coin.getSymbol());
        coinResource.addProperty(CRYS.type, coinModel.createResource(CRYS_URI + "Coin"));
        coinResource.addProperty(CRYS.name, coin.getName());
        coinResource.addProperty(CRYS.rank, coin.getRank().toString());
        coinResource.addProperty(CRYS.symbol, coin.getSymbol());
        coinResource.addProperty(CRYS.logoUrl, coin.getLogoURL() != null ? coin.getLogoURL() : "");
        coinResource.addProperty(CRYS.supply, coin.getSupply() != null ? coin.getSupply().toString() : "");
        coinResource.addProperty(CRYS.maxSupply, coin.getMaxSupply() != null ? coin.getMaxSupply().toString() : "");
        coinResource.addProperty(CRYS.marketCapUsd, coin.getMarketCapUsd() != null ? coin.getMarketCapUsd().toString() : "");
        coinResource.addProperty(CRYS.volumeUsd24hr, coin.getVolumeUsd24hr() != null ? coin.getVolumeUsd24hr().toString() : "");
        coinResource.addProperty(CRYS.priceUsd, coin.getPriceUsd() != null ? coin.getPriceUsd().toString() : "");
        coinResource.addProperty(CRYS.changePercentage24hr, coin.getChangePercentage24hr() != null ? coin.getChangePercentage24hr().toString() : "");
        coinResource.addProperty(CRYS.vwap24hr, coin.getVwap24hr() != null ? coin.getVwap24hr().toString() : "");

        dataset.addNamedModel(COIN_URI, coinModel);

        System.out.println(coin.getName());

        dataset.commit();
        dataset.end();
    }
}
