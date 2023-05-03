package com.raminorujov.solrpaging.utility;

import com.raminorujov.solrpaging.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.raminorujov.solrpaging.domain.Constants.*;
import static com.raminorujov.solrpaging.domain.Constants.PUBLISHER;
import static com.raminorujov.solrpaging.domain.Constants.PUBLISH_YEAR;

/**
 * Created by raminorujov on 23/06/2017.
 */
@Slf4j
public class SolrUtility {

    public static void clearSolrDocs(SolrClient solrClient) throws Exception {
        UpdateRequest req = new UpdateRequest();
        req.setDeleteQuery(Collections.singletonList("*:*"));
        solrClient.request(req);
        solrClient.commit();
        log.info("Cleared all solr docs");
    }

    public static void createCore(SolrClient solrClient, String core) throws Exception {
        CoreAdminResponse response = CoreAdminRequest.getStatus(core, solrClient);

        if (response.getCoreStatus(core).size() < 1) {
            CoreAdminRequest.Create aCreateRequest = new CoreAdminRequest.Create();
            aCreateRequest.setCoreName(core);
            aCreateRequest.setInstanceDir(core);
            aCreateRequest.process(solrClient);
            log.info("Created core {}", core);
        } else {
            log.info("Core name {} already exists", core);
        }
    }

    public static void deleteCore(SolrClient solrClient, String core) throws Exception {
        CoreAdminResponse response = CoreAdminRequest.getStatus(core, solrClient);
        if (response.getCoreStatus(core).size() != 0) {
            CoreAdminRequest.unloadCore(core, true, true, solrClient);
            log.info("Deleted core {}", core);
        } else {
            log.info("Core {} does not exist", core);
        }
    }

    public static List<Book> parseCSV(URI resource) throws Exception {
        List<Book> books = new ArrayList<>();
        Reader reader = new FileReader(new File(resource));
        CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

        parser.getRecords().forEach(record -> {
            // BOOK_ID,TITLE,AUTHOR,CATEGORY,PUBLISHER,PUBLISH_YEAR
            String id = record.get(BOOK_ID);
            String title = record.get(TITLE);
            String author = record.get(AUTHOR);
            String category = record.get(CATEGORY);
            String publisher = record.get(PUBLISHER);
            String publishYear = record.get(PUBLISH_YEAR);
            Book book = new Book(id, title, author, category, publisher, publishYear);
            books.add(book);
        });

        return books;
    }

    public static void loadToSolr(SolrClient solrClient, List<Book> books) throws Exception {
        List<SolrInputDocument> solrDocs = new ArrayList<>();

        books.forEach(book -> {
            SolrInputDocument document = new SolrInputDocument();
            document.addField(BOOK_ID, book.getId());
            document.addField(TITLE, book.getTitle());
            document.addField(CATEGORY, book.getCategory());
            document.addField(AUTHOR, book.getAuthor());
            document.addField(PUBLISHER, book.getPublisher());
            document.addField(PUBLISH_YEAR, book.getPublishYear());
            solrDocs.add(document);
        });

        solrClient.add(solrDocs);
        solrClient.commit();
        log.debug("Added {} books into solr", solrDocs.size());
    }

    public static void loadFromResource(SolrClient solrClient, URI resource) throws Exception {
        List<Book> books = parseCSV(resource);
        loadToSolr(solrClient, books);
    }

    public static Book convert(SolrDocument document) {
        Book book = new Book();
        book.setId(document.getFieldValue(BOOK_ID).toString());
        book.setTitle(document.getFieldValue(TITLE).toString());
        book.setAuthor(document.getFieldValue(AUTHOR).toString());
        book.setCategory(document.getFieldValue(CATEGORY).toString());
        book.setPublisher(document.getFieldValue(PUBLISHER).toString());
        book.setPublishYear(document.getFieldValue(PUBLISH_YEAR).toString());
        return book;
    }

}
