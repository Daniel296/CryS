package com.wade.crys.utils.rdf;

/**
 * @author doana
 */
public enum CrysOntologyEnum {

	GET_USER_BY_EMAIL_AND_PASSWORD_QRY(
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
					"      crys:token ?token ;\n" +
					"      crys:password ?password ;\n" +
					"      crys:emailNotification ?emailNotification ;\n" +
					"FILTER (?email=\"%1s\")" +
					" }\n" +
					"LIMIT 1 \n"
	),

	GET_ALL_USERS_QRY(
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
					"      crys:token ?token ;\n" +
					"      crys:password ?password ;\n" +
					"      crys:emailNotification ?emailNotification ;\n" +
					" }\n"
	);

	private String code;

	CrysOntologyEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}
