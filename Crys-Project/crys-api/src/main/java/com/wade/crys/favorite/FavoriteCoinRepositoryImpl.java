package com.wade.crys.favorite;

import static com.wade.crys.utils.rdf.CRYS.COIN_URI;
import static com.wade.crys.utils.rdf.CRYS.CRYS_URI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wade.crys.coin.interfaces.CoinService;
import com.wade.crys.coin.model.Coin;
import com.wade.crys.utils.rdf.CRYS;
import com.wade.crys.utils.rdf.CrysOntologyEnum;

@Repository
public class FavoriteCoinRepositoryImpl implements FavoriteCoinRepository {

	@Autowired
	private Dataset dataset;

	@Autowired
	private CoinService coinService;

	@Override
	public List<Coin> getFavoriteCoinsForUser(String userId) {

		List<String> coinIds = new ArrayList<>();

		dataset.begin(ReadWrite.READ);
		try {

			ResultSet rs = QueryExecutionFactory.create(
					String.format(CrysOntologyEnum.GET_FAVORITE_COINS_FOR_USER_QRY.getCode(), userId), dataset).execSelect();

			while(rs.hasNext()) {

				QuerySolution qs = rs.next();

				coinIds.add(qs.get("hasFavoriteCoin").toString());
			}
		} catch(Exception e) {

			System.out.println(e);
		} finally {
			dataset.end();
		}

		List<Coin> favoriteCoins = new ArrayList<>();
		coinIds.forEach(id -> coinService.getCoinById(id).ifPresent(favoriteCoins::add));

		return favoriteCoins;
	}

	@Override
	public void addFavoriteCoinToUser(String userId, String coinId) {

		dataset.begin(ReadWrite.WRITE);

		Model favoriteCoinModel = dataset.getDefaultModel();

		Resource favoriteCoinResource = favoriteCoinModel.createResource(CRYS_URI + "user-" + userId);
		favoriteCoinResource.addProperty(CRYS.hasFavoriteCoin, coinId);

		dataset.addNamedModel(COIN_URI + userId, favoriteCoinModel);
		dataset.commit();
		dataset.end();
	}

	@Override
	public void deleteFavoriteCoinForUser(String userId, String coinId) {

		dataset.begin(ReadWrite.WRITE);

		try {

			UpdateRequest req = UpdateFactory.create(String.format(CrysOntologyEnum.DELETE_FAVORITE_COIN_FOR_USER_QRY.getCode(), userId, coinId)) ;
			UpdateAction.execute(req, dataset);

			dataset.commit();
		} catch (Exception e) {

			System.out.println(e);
		} finally {
			dataset.end();
		}

		dataset.begin(ReadWrite.READ);
		try(QueryExecution qExec = QueryExecutionFactory.create("SELECT ?s ?p ?o WHERE { ?s ?p ?o }", dataset)) {
			ResultSet rs = qExec.execSelect() ;
			ResultSetFormatter.out(rs) ;
		}
		dataset.end();
	}
}
