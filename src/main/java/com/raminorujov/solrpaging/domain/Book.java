package com.raminorujov.solrpaging.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Updated By miliariadnane on 03/05/2023
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data @ToString
@AllArgsConstructor @NoArgsConstructor
public class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    private String publisher;
    private String publishYear;
}
