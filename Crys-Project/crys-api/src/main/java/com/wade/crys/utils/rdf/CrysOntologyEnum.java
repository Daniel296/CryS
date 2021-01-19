package com.wade.crys.utils.rdf;

public enum CrysOntologyEnum {

	GET_USER_BY_EMAIL_QRY(
			"PREFIX crys: <http://www.semanticweb.org/crys#> \n" +
					"PREFIX user: <http://www.semanticweb.org/crys/User/> \n" +
					"PREFIX foaf: <http://xmlns.com/foaf/0.1#> \n" +
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
					"\n" +
					"SELECT * " +
					"WHERE { \n" +
					"?user rdf:type crys:User ;\n" +
					"      foaf:firstName ?firstName ;\n" +
					"      foaf:lastName ?lastName ;\n" +
					"      foaf:telephone ?telephone ;\n" +
					"      foaf:email ?email ;\n" +
					"      crys:password ?password ;\n" +
					"      crys:emailNotification ?emailNotification ;\n" +
					"FILTER (?email=\"%1s\")" +
					" }\n" +
					"LIMIT 1 \n"
	),

	GET_USER_BY_ID_QRY(
			"PREFIX crys: <http://www.semanticweb.org/crys#> \n" +
					"PREFIX user: <http://www.semanticweb.org/crys/User/> \n" +
					"PREFIX foaf: <http://xmlns.com/foaf/0.1#> \n" +
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
					"\n" +
					"SELECT * " +
					"WHERE { \n" +
					"?user rdf:type crys:User ;\n" +
					"      foaf:firstName ?firstName ;\n" +
					"      foaf:lastName ?lastName ;\n" +
					"      foaf:telephone ?telephone ;\n" +
					"      foaf:email ?email ;\n" +
					"      crys:password ?password ;\n" +
					"      crys:emailNotification ?emailNotification ;\n" +
					"FILTER (contains(str(?user), '%1s'))" +
					" }\n" +
					"LIMIT 1 \n"
	),

	GET_COIN_BY_ID_QRY(
			"PREFIX crys: <http://www.semanticweb.org/crys#> \n" +
					"PREFIX coin: <http://www.semanticweb.org/crys/Coin/> \n" +
					"PREFIX foaf: <http://xmlns.com/foaf/0.1#> \n" +
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
					"\n" +
					"SELECT * " +
					"WHERE { \n" +
					"?coin rdf:type crys:Coin ;\n" +
					"      crys:id ?id ;\n" +
					"      crys:name ?name ;\n" +
					"      crys:rank ?rank ;\n" +
					"      crys:symbol ?symbol ;\n" +
					"      crys:logoUrl ?logoUrl ;\n" +
					"      crys:supply ?supply ;\n" +
					"      crys:maxSupply ?maxSupply ;\n" +
					"      crys:marketCapUsd ?marketCapUsd ;\n" +
					"      crys:volumeUsd24hr ?volumeUsd24hr ;\n" +
					"      crys:priceUsd ?priceUsd ;\n" +
					"      crys:changePercentage24hr ?changePercentage24hr ;\n" +
					"      crys:vwap24hr ?vwap24hr ;\n" +
					"FILTER( ?id = \"%1s\") \n" +
					" }\n" +
					"LIMIT 1\n"
	),

	GET_ALL_COINS_ORDERED_BY_RANK_QRY(
			"PREFIX crys: <http://www.semanticweb.org/crys#> \n" +
					"PREFIX coin: <http://www.semanticweb.org/crys/Coin/> \n" +
					"PREFIX foaf: <http://xmlns.com/foaf/0.1#> \n" +
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
					"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> \n" +
					"\n" +
					"SELECT * \n" +
					"WHERE { \n" +
					"?coin rdf:type crys:Coin ;\n" +
					"      crys:id ?id ;\n" +
					"      crys:name ?name ;\n" +
					"      crys:rank ?rank ;\n" +
					"      crys:symbol ?symbol ;\n" +
					"      crys:logoUrl ?logoUrl ;\n" +
					"      crys:supply ?supply ;\n" +
					"      crys:maxSupply ?maxSupply ;\n" +
					"      crys:marketCapUsd ?marketCapUsd ;\n" +
					"      crys:volumeUsd24hr ?volumeUsd24hr ;\n" +
					"      crys:priceUsd ?priceUsd ;\n" +
					"      crys:changePercentage24hr ?changePercentage24hr ;\n" +
					"      crys:vwap24hr ?vwap24hr ;\n" +
					" }\n" +
					"ORDER BY xsd:integer(replace(?rank, ' ', ''))\n"
	),

	UPDATE_COIN_QRY(
			"PREFIX coin: <http://www.semanticweb.org/crys/Coin/> \n" +
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
					"PREFIX crys: <http://www.semanticweb.org/crys#> \n" +
					"\n" +
					"DELETE {" +
					"	?coin crys:priceUsd ?priceUsd ; \n" +
					"			crys:rank ?rank ; \n" +
					"			crys:symbol ?symbol ; \n" +
					"			crys:supply ?supply ; \n" +
					"			crys:maxSupply ?maxSupply ; \n" +
					"			crys:marketCapUsd ?marketCapUsd ; \n" +
					"			crys:volumeUsd24hr ?volumeUsd24hr ; \n" +
					"			crys:changePercentage24hr ?changePercentage24hr ; \n" +
					"			crys:vwap24hr ?vwap24hr ; \n" +
					"}\n" +
					"INSERT { \n" +
					"	?coin crys:priceUsd \"%1s\" ; \n" +
					"			crys:rank \"%2s\" ; \n" +
					"			crys:symbol \"%3s\" ; \n" +
					"			crys:supply \"%4s\" ; \n" +
					"			crys:maxSupply \"%5s\" ; \n" +
					"			crys:marketCapUsd \"%6s\" ; \n" +
					"			crys:volumeUsd24hr \"%7s\" ; \n" +
					"			crys:changePercentage24hr \"%8s\" ; \n" +
					"			crys:vwap24hr \"%9s\" ; \n" +
					"}\n" +
					"WHERE\n" +
					" { ?coin crys:id \"%10s\" ;\n" +
					"			crys:priceUsd ?priceUsd ; \n" +
					"			crys:rank ?rank ; \n" +
					"			crys:symbol ?symbol ; \n" +
					"			crys:supply ?supply ; \n" +
					"			crys:maxSupply ?maxSupply ; \n" +
					"			crys:marketCapUsd ?marketCapUsd ; \n" +
					"			crys:volumeUsd24hr ?volumeUsd24hr ; \n" +
					"			crys:changePercentage24hr ?changePercentage24hr ; \n" +
					"			crys:vwap24hr ?vwap24hr ; \n" +
					" }"
	),

	DELETE_ALL_COINS_QRY (
			"PREFIX coin: <http://www.semanticweb.org/crys/Coin/> \n" +
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
					"PREFIX crys: <http://www.semanticweb.org/crys#> \n" +
					"\n" +
					"DELETE {" +
					"	?coin rdf:type crys:Coin ; \n" +
					"			crys:id ?id ; \n" +
					"			crys:name ?name ; \n" +
					"			crys:rank ?rank ; \n" +
					"	        crys:priceUsd ?priceUsd ; \n" +
					"			crys:logoUrl ?logoUrl ; \n" +
					"			crys:symbol ?symbol ; \n" +
					"			crys:supply ?supply ; \n" +
					"			crys:maxSupply ?maxSupply ; \n" +
					"			crys:marketCapUsd ?marketCapUsd ; \n" +
					"			crys:volumeUsd24hr ?volumeUsd24hr ; \n" +
					"			crys:changePercentage24hr ?changePercentage24hr ; \n" +
					"			crys:vwap24hr ?vwap24hr ; \n" +
					"}\n" +
					"WHERE\n" +
					" { ?coin rdf:type crys:Coin ;\n" +
					"			crys:id ?id ; \n" +
					"			crys:name ?name ; \n" +
					"			crys:rank ?rank ; \n" +
					"			crys:priceUsd ?priceUsd ; \n" +
					"			crys:logoUrl ?logoUrl ; \n" +
					"			crys:symbol ?symbol ; \n" +
					"			crys:supply ?supply ; \n" +
					"			crys:maxSupply ?maxSupply ; \n" +
					"			crys:marketCapUsd ?marketCapUsd ; \n" +
					"			crys:volumeUsd24hr ?volumeUsd24hr ; \n" +
					"			crys:changePercentage24hr ?changePercentage24hr ; \n" +
					"			crys:vwap24hr ?vwap24hr ; \n" +
					" }"
	),

	GET_FAVORITE_COINS_FOR_USER_QRY (
			"PREFIX crys: <http://www.semanticweb.org/crys#> \n" +
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
					"\n" +
					"SELECT * \n" +
					"WHERE { \n" +
					"?user rdf:type crys:User ;\n" +
					"      crys:hasFavoriteCoin ?hasFavoriteCoin ;\n" +
					"FILTER (contains(str(?user), \"%1s\"))" +
					" }\n"
	),

	DELETE_FAVORITE_COIN_FOR_USER_QRY (
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
					"PREFIX crys: <http://www.semanticweb.org/crys#> \n" +
					"\n" +
					"DELETE DATA { \n " +
					"	 <http://www.semanticweb.org/crys#user-%1s> crys:hasFavoriteCoin \"%2s\" ; \n" +
					"}\n"
	);

	private String code;

	CrysOntologyEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}
