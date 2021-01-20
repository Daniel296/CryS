package com.wade.crys.config;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.tdb.TDBLoader;
import org.apache.jena.tdb.base.file.Location;
import org.apache.jena.tdb.setup.StoreParams;
import org.apache.jena.tdb.setup.StoreParamsBuilder;
import org.apache.tomcat.jni.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {


	private static final String ONTOLOGY_DIRECTORY_PATH = "crys-api/src/main/resources/data/database";
	private static final String ONTOLOGY_PATH = "crys-api/src/main/resources/data/rdf_xml_crys_ontology.owl";

	@Bean
	public Dataset dataset() {

		try{
			TDBFactory.setup(Location.create(ONTOLOGY_DIRECTORY_PATH), getStoreParams());

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

	private StoreParams getStoreParams() {

		return StoreParamsBuilder.create()
				.blockSize(8192)
				.blockReadCacheSize(10000)
				.blockWriteCacheSize(4000)
				.node2NodeIdCacheSize(10000)
				.nodeId2NodeCacheSize(50000)
				.nodeMissCacheSize(100)
				.indexNode2Id("node2id")
				.indexId2Node("nodes")
				.build();
	}

}
