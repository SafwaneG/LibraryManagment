package com.example.library.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanCode;
    @ManyToOne
    private Adherent adherent;
    @ManyToOne
    private Book borrowedBook;
    private LocalDateTime loanDate;
    private LocalDateTime expectedReturnDate;
    private LocalDateTime effectiveReturnDate;

    public Loan() {
        this.loanDate = LocalDateTime.now();
        this.expectedReturnDate = LocalDateTime.now().plusDays(7);
        this.effectiveReturnDate = null;
    }
}
