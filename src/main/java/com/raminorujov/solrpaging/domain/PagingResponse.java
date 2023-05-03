package com.raminorujov.solrpaging.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by raminorujov on 23/06/2017.
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Setter @Getter
@ToString
public class PagingResponse {
    private PagingData pagingData;
    private List<Book> books;
}
