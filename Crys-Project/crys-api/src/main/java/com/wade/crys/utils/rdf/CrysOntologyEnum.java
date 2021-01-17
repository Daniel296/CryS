package com.wade.crys.utils.rdf;

/**
 * @author doana
 */
public enum CrysOntologyEnum {

	GET_USER_BY_EMAIL_QRY(
			"PREFIX crys: <http://www.semanticweb.org/crys#> \n" +
					"PREFIX user: <http://www.semanticweb.org/crys/User/> \n" +
					"PREFIX foaf: <http://xmlns.com/foaf/0.1#> \n" +
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
					"PREFIX email: <http://xmlns.com/foaf/0.1#email> \n" +
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
					"PREFIX email: <http://xmlns.com/foaf/0.1#email> \n" +
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

	GET_ALL_COINS_ORDERED_BY_RANK_QRY(
			"PREFIX crys: <http://www.semanticweb.org/crys#> \n" +
					"PREFIX coin: <http://www.semanticweb.org/crys/Coin/> \n" +
					"PREFIX foaf: <http://xmlns.com/foaf/0.1#> \n" +
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
					"\n" +
					"SELECT * " +
					"WHERE { \n" +
					"?coin rdf:type crys:Coin ;\n" +
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
					"OREDER BY ASC(?rank)\n"
	);

	private String code;

	CrysOntologyEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}
