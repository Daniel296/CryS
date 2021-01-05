package com.wade.crys.utils.rdf;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

public class CrySVocabulary {

    private static final String BASE_URI = " #%s";
    private static final String COIN_URI = "http://xmlns.com/crys/coin#%s";
    private static final String COIN_HISTORY_URI = "http://xmlns.com/crys/coin-history#%s";
    private static final String USER_URI = "http://xmlns.com/crys/user#%s";
    private static final String ALERT_URI = "http://xmlns.com/crys/alert#%s";
    private static final String NEWS_URI = "http://xmlns.com/crys/news#%s";

    private static final Model model = ModelFactory.createDefaultModel();

    public static final Property coin;

    static {
        coin = model.createProperty(getURI(COIN_URI, ""));
    }

    private static String getURI(String uri, String property) {
        return String.format(uri, property);
    }

}
