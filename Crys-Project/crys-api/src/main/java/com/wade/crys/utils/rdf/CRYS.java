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

	private static final Model m = ModelFactory.createDefaultModel();

	public static Property type = m.createProperty(CRYS_TYPE);

	public static Property password = m.createProperty(CRYS_URI + "password");
	public static Property emailNotification = m.createProperty(CRYS_URI + "emailNotification");
	public static Property token = m.createProperty(CRYS_URI + "token");

}
