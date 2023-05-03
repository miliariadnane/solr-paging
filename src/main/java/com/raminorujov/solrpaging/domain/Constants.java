package com.raminorujov.solrpaging.domain;

import static org.apache.solr.common.params.CursorMarkParams.CURSOR_MARK_START;

/**
 * Updated By miliariadnane on 03/05/2023
 */
public class Constants {
    public static final String ID = "id";
    public static final String BOOK_ID = "book_id";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String CATEGORY = "category";
    public static final String PUBLISHER = "publisher";
    public static final String PUBLISH_YEAR = "publish_year";

    public static final String PAGE_SIZE = "page_size";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String PAGE_NUMBER = "page_num";
    public static final String DEFAULT_PAGE_NUMBER = "1";
    public static final String SORT_COLUMN = "sort_col";
    public static final String DEFAULT_SORT_COLUMN = "book_id";
    public static final String SORT_DIRECTION = "sort_dir";
    public static final String DEFAULT_SORT_DIRECTION = "1";
    public static final String CURSOR_MARK = "cursor_mark";
    public static final String DEFAULT_CURSOR_MARK = CURSOR_MARK_START;
}
