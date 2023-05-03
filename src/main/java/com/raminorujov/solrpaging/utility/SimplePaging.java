package com.raminorujov.solrpaging.utility;

import com.raminorujov.solrpaging.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.util.ArrayList;
import java.util.List;

import static com.raminorujov.solrpaging.domain.Constants.BOOK_ID;
import static com.raminorujov.solrpaging.domain.Constants.ID;

/**
 * Created by raminorujov on 23/06/2017.
 */
@Slf4j
public class SimplePaging {

    public static void main(String[] args) throws Exception {
        String core = "books";
        String solrUrl = "http://localhost:8983/solr/" + core;
        SolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();

        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setRows(7);
        query.setStart(0);
        query.addSort(BOOK_ID, ORDER.asc);
        query.addSort(ID, ORDER.asc);

        QueryResponse response = solrClient.query(query);
        SolrDocumentList results = response.getResults();
        List<Book> books = new ArrayList<>();

        results.forEach(document -> {
            books.add(SolrUtility.convert(document));
        });

        log.info("Got {} books from solr", results.getNumFound());
        log.info("Books = {}", books);

    }
}
