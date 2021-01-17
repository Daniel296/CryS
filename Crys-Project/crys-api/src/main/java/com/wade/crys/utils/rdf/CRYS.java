package com.wade.crys.utils.rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

/**
 * @author doana
 */
public class CRYS {

	public static final String CRYS_URI = "http://www.semanticweb.org/crys#";
	public static final String CRYS_TYPE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";

	public static final String USER_URI = "http://www.semanticweb.org/crys/User";
	public static final String COIN_URI = "http://www.semanticweb.org/crys/Coin";

	private static final Model m = ModelFactory.createDefaultModel();

	public static Property type = m.createProperty(CRYS_TYPE);

	/* ===== User properties ===== */
	public static Property password = m.createProperty(CRYS_URI + "password");
	public static Property emailNotification = m.createProperty(CRYS_URI + "emailNotification");

	/* ===== Coin properties ===== */
	public static Property name = m.createProperty(CRYS_URI + "name");
	public static Property rank = m.createProperty(CRYS_URI + "rank");
	public static Property symbol = m.createProperty(CRYS_URI + "symbol");
	public static Property logoUrl = m.createProperty(CRYS_URI + "logoUrl");
	public static Property supply = m.createProperty(CRYS_URI + "supply");
	public static Property maxSupply = m.createProperty(CRYS_URI + "maxSupply");
	public static Property marketCapUsd = m.createProperty(CRYS_URI + "marketCapUsd");
	public static Property volumeUsd24hr = m.createProperty(CRYS_URI + "volumeUsd24hr");
	public static Property priceUsd = m.createProperty(CRYS_URI + "priceUsd");
	public static Property changePercentage24hr = m.createProperty(CRYS_URI + "changePercentage24hr");
	public static Property vwap24hr = m.createProperty(CRYS_URI + "vwap24hr");

}
