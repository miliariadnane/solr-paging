package com.raminorujov.solrpaging.utility;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

import static com.raminorujov.solrpaging.utility.SolrUtility.clearSolrDocs;
import static com.raminorujov.solrpaging.utility.SolrUtility.loadFromResource;

/**
 * Created by raminorujov on 23/06/2017.
 */
public class DocumentLoader {

    public static void main(String[] args) throws Exception {
        DocumentLoader loader = new DocumentLoader();
        URI csv = loader.getClass().getClassLoader().getResource("books.csv").toURI();
        String core = "books";
        String solrUrl = "http://localhost:8983/solr/" + core;
        SolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        clearSolrDocs(solrClient);
        loadFromResource(solrClient, csv);
    }
}


