package com.wade.crys.utils.rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

/**
 * @author doana
 */
public class FOAF {

	private static final String FOAF_URI = "http://xmlns.com/foaf/0.1#";

	private static final Model m = ModelFactory.createDefaultModel();

	public static Property firstName = m.createProperty(FOAF_URI + "firstName");
	public static Property lastName = m.createProperty(FOAF_URI + "lastName");
	public static Property email = m.createProperty(FOAF_URI + "email");
	public static Property telephone = m.createProperty(FOAF_URI + "telephone");
}
