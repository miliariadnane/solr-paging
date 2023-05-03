package com.raminorujov.solrpaging.service;

import com.raminorujov.solrpaging.domain.Book;
import com.raminorujov.solrpaging.domain.PagingData;
import com.raminorujov.solrpaging.domain.PagingResponse;
import com.raminorujov.solrpaging.utility.SolrUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.raminorujov.solrpaging.domain.Constants.ID;
import static org.apache.solr.client.solrj.SolrQuery.ORDER.asc;
import static org.apache.solr.client.solrj.SolrQuery.ORDER.desc;
import static org.apache.solr.common.params.CursorMarkParams.CURSOR_MARK_PARAM;

/**
 * Created by raminorujov on 23/06/2017.
 */
@Service
@Slf4j
public class SolrPagingService {

    @Value("${solr.url}")
    private String solrUrl;

    public SolrClient getSolrClient() {
        SolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
        return solrClient;
    }

    public PagingResponse getBooksWithBasicPaging(PagingData pagingData) {
        PagingResponse response = new PagingResponse();

        try {
            SolrClient solrClient = getSolrClient();

            SolrQuery query = new SolrQuery();
            query.setQuery("*:*"); // get all documents
            query.setRows(pagingData.getPageSize());
            query.setStart(pagingData.getPageSize() * (pagingData.getPageNumber() - 1));

            ORDER sortOrder = asc;
            if(pagingData.getSortDirection() == -1) {
                sortOrder = desc;
            }
            query.addSort(pagingData.getSortColumn(), sortOrder);
            query.addSort(ID, asc);

            QueryResponse solrResponse = solrClient.query(query);;
            SolrDocumentList results = solrResponse.getResults();

            pagingData.setRowCount((int)results.getNumFound());
            pagingData.setPageCount(pagingData.getRowCount() / pagingData.getPageSize());

            if(pagingData.getRowCount() % pagingData.getPageSize() != 0) {
                pagingData.setPageCount(pagingData.getPageCount() + 1);
            }

            List<Book> books = new ArrayList<>();
            results.forEach(document -> {
                books.add(SolrUtility.convert(document));
            });

            log.info("Got {} books from solr", results.getNumFound());

            response.setBooks(books);
            response.setPagingData(pagingData);

        } catch (Exception e) {
            log.error("Error getting books from solr", e);
            throw new RuntimeException("Error getting books from solr", e);
        }

        return response;
    }

    public PagingResponse getBooksWithAdvancedPaging(PagingData pagingData) {
        PagingResponse response = new PagingResponse();

        try {
            SolrClient solrClient = getSolrClient();

            SolrQuery query = new SolrQuery();
            query.setQuery("*:*"); // get all documents
            query.setRows(pagingData.getPageSize());
            query.set(CURSOR_MARK_PARAM, pagingData.getCursorMark());

            ORDER sortOrder = asc;
            if(pagingData.getSortDirection() == -1) {
                sortOrder = desc;
            }
            query.addSort(pagingData.getSortColumn(), sortOrder);
            query.addSort(ID, asc);

            QueryResponse solrResponse = solrClient.query(query);;
            SolrDocumentList results = solrResponse.getResults();

            pagingData.setRowCount((int)results.getNumFound());
            pagingData.setPageCount(pagingData.getRowCount() / pagingData.getPageSize());

            if(pagingData.getRowCount() % pagingData.getPageSize() != 0) {
                pagingData.setPageCount(pagingData.getPageCount() + 1);
            }

            pagingData.setCursorMark(solrResponse.getNextCursorMark());

            List<Book> books = new ArrayList<>();
            results.forEach(document -> {
                books.add(SolrUtility.convert(document));
            });

            log.info("Got {} books from solr", results.getNumFound());

            response.setBooks(books);
            response.setPagingData(pagingData);

        } catch (Exception e) {
            log.error("Error getting books from solr", e);
            throw new RuntimeException("Error getting books from solr", e);
        }

        return response;
    }
}
