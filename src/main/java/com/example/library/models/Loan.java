package com.example.library.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
@Data
@Table("loans")
public class Loan {
    @Id
    private Long loanCode;
    private Long adherentId;
    private Long borrowedBookId;
    private LocalDateTime loanDate;
    private LocalDateTime expectedReturnDate;
    private LocalDateTime effectiveReturnDate;

    public Loan(Long adherentId, Long borrowedBookId) {
        this.adherentId = adherentId;
        this.borrowedBookId = borrowedBookId;
        this.loanDate = LocalDateTime.now();
        this.expectedReturnDate = LocalDateTime.now().plusDays(7);
        this.effectiveReturnDate = null;
    }
}
