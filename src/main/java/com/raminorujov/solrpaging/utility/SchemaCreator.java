package com.raminorujov.solrpaging.utility;

import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.raminorujov.solrpaging.domain.Constants.*;
import static com.raminorujov.solrpaging.utility.SolrUtility.*;

/**
 * Created by raminorujov on 23/06/2017.
 */
@Slf4j
public class SchemaCreator {

    public static void main(String[] args) throws Exception {
        String solrUrl = "http://localhost:8983/solr/books";
        SolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        clearSolrDocs(solrClient);
        createFields(solrClient);
    }

    public static void createFields(SolrClient solrClient) throws Exception {
        Map<String, Object> fieldAttributes = new LinkedHashMap<>();
        fieldAttributes.put("name", BOOK_ID);
        fieldAttributes.put("type", "string");
        fieldAttributes.put("stored", "true");
        fieldAttributes.put("multiValued", "false");

        SchemaRequest.AddField addFieldRequest = new SchemaRequest.AddField(fieldAttributes);
        addFieldRequest.process(solrClient);

        fieldAttributes.put("name", TITLE);
        fieldAttributes.put("type", "string");
        fieldAttributes.put("stored", "true");
//        fieldAttributes.put("multiValued", "false");
        addFieldRequest = new SchemaRequest.AddField(fieldAttributes);
        addFieldRequest.process(solrClient);

        fieldAttributes.put("name", AUTHOR);
        fieldAttributes.put("type", "string");
        fieldAttributes.put("stored", "true");
//        fieldAttributes.put("multiValued", "false");
        addFieldRequest = new SchemaRequest.AddField(fieldAttributes);
        addFieldRequest.process(solrClient);

        fieldAttributes.put("name", CATEGORY);
        fieldAttributes.put("type", "string");
        fieldAttributes.put("stored", "true");
//        fieldAttributes.put("multiValued", "false");
        addFieldRequest = new SchemaRequest.AddField(fieldAttributes);
        addFieldRequest.process(solrClient);

        fieldAttributes.put("name", PUBLISHER);
        fieldAttributes.put("type", "string");
        fieldAttributes.put("stored", "true");
//        fieldAttributes.put("multiValued", "false");
        addFieldRequest = new SchemaRequest.AddField(fieldAttributes);
        addFieldRequest.process(solrClient);

        fieldAttributes.put("name", PUBLISH_YEAR);
        fieldAttributes.put("type", "string");
        fieldAttributes.put("stored", "true");
//        fieldAttributes.put("multiValued", "false");
        addFieldRequest = new SchemaRequest.AddField(fieldAttributes);
        addFieldRequest.process(solrClient);

        log.info("Created solr schema");
    }
}
