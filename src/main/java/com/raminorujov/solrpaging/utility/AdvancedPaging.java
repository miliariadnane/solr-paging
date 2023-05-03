package com.raminorujov.solrpaging.utility;

import com.raminorujov.solrpaging.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.raminorujov.solrpaging.domain.Constants.BOOK_ID;
import static com.raminorujov.solrpaging.domain.Constants.ID;
import static org.apache.solr.common.params.CursorMarkParams.CURSOR_MARK_PARAM;
import static org.apache.solr.common.params.CursorMarkParams.CURSOR_MARK_START;

/**
 * Created by raminorujov on 23/06/2017.
 */
@Slf4j
public class AdvancedPaging {

    public static void main(String[] args) throws Exception {
        String core = "books";
        String solrUrl = "http://localhost:8983/solr/" + core;
        SolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();

        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setRows(10);
        query.addSort(BOOK_ID, SolrQuery.ORDER.asc);
        query.addSort(ID, SolrQuery.ORDER.asc);
        query.set(CURSOR_MARK_PARAM, CURSOR_MARK_START);

        QueryResponse response = solrClient.query(query);
        SolrDocumentList results = response.getResults();
        List<Book> books = new ArrayList<>();

        results.forEach(document -> {
            books.add(SolrUtility.convert(document));
        });

        String cursorMark = response.getNextCursorMark();
        log.info("next cursor mark = {}", cursorMark);

        log.info("Got {} books from solr", results.getNumFound());
        log.info("Books = {}", books);

    }
}
