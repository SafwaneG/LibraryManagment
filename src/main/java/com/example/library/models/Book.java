package com.example.library.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("books")
public class Book {
    @Id
    private Long bookCode;
    private String bookTitle;
    private Long bookAuthor;
    private int samplesTotalNumber;
    private int availableSamples;

    public Book(String bookTitle, Long bookAuthor, int samplesTotalNumber, int availableSamples) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.samplesTotalNumber = samplesTotalNumber;
        this.availableSamples = availableSamples;
    }
}
