package com.wade.crys.config;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.tdb.TDBLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {


	private static final String ONTOLOGY_DIRECTORY_PATH = "crys-api/src/main/resources/data/database";
	private static final String ONTOLOGY_PATH = "crys-api/src/main/resources/data/rdf_xml_crys_ontology.owl";

	@Bean
	public Dataset dataset() {

		try{

			Dataset dataset = TDBFactory.createDataset(ONTOLOGY_DIRECTORY_PATH);
			dataset.begin(ReadWrite.WRITE) ;

			Model model = dataset.getDefaultModel();
			TDBLoader.loadModel(model, ONTOLOGY_PATH);

			dataset.commit();
			dataset.end();

			return dataset;
		} catch(Exception ex) {

			System.out.println("##### Error Function: createDataset #####");
			System.out.println(ex.getMessage());

			return null;
		}
	}

}
