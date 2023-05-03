package com.raminorujov.solrpaging.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Updated By miliariadnane on 03/05/2023
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter @Setter
@ToString
public class PagingData {

    private int pageSize;
    private int pageNumber;
    private String sortColumn;
    private int sortDirection;
    private int rowCount;
    private int pageCount;
    private String cursorMark;
}
