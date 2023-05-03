package com.raminorujov.solrpaging.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Updated By miliariadnane on 03/05/2023
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Setter @Getter
@ToString
public class PagingResponse {
    private PagingData pagingData;
    private List<Book> books;
}
