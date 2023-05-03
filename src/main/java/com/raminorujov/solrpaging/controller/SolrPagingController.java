package com.raminorujov.solrpaging.controller;

import com.raminorujov.solrpaging.domain.PagingData;
import com.raminorujov.solrpaging.domain.PagingResponse;
import com.raminorujov.solrpaging.service.SolrPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.raminorujov.solrpaging.domain.Constants.*;

/**
 * Updated By miliariadnane on 03/05/2023
 */

@RestController
public class SolrPagingController {

    @Autowired
    private SolrPagingService service;

    @RequestMapping(value = "/basic-paging",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagingResponse> getDataWithBasicPaging(
            @RequestParam(value = PAGE_SIZE, required = false, defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = PAGE_NUMBER, required = false, defaultValue = DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = SORT_COLUMN, required = false, defaultValue = DEFAULT_SORT_COLUMN) String sortColumn,
            @RequestParam(value = SORT_DIRECTION, required = false, defaultValue = DEFAULT_SORT_DIRECTION) int sortDirection) {

        PagingData pagingData = new PagingData();
        pagingData.setPageSize(pageSize);
        pagingData.setPageNumber(pageNumber);
        pagingData.setSortColumn(sortColumn);
        pagingData.setSortDirection(sortDirection);

        PagingResponse response = service.getBooksWithBasicPaging(pagingData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/advanced-paging",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagingResponse> getDataWithAdvancedPaging(
            @RequestParam(value = PAGE_SIZE, required = false, defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = SORT_COLUMN, required = false, defaultValue = DEFAULT_SORT_COLUMN) String sortColumn,
            @RequestParam(value = SORT_DIRECTION, required = false, defaultValue = DEFAULT_SORT_DIRECTION) int sortDirection,
            @RequestParam(value = CURSOR_MARK, required = false, defaultValue = DEFAULT_CURSOR_MARK) String cursorMark) {

        PagingData pagingData = new PagingData();
        pagingData.setPageSize(pageSize);
        pagingData.setSortColumn(sortColumn);
        pagingData.setSortDirection(sortDirection);
        pagingData.setCursorMark(cursorMark);

        PagingResponse response = service.getBooksWithAdvancedPaging(pagingData);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
