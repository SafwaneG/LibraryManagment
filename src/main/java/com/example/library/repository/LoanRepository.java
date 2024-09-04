package com.example.library.repository;

import com.example.library.models.Loan;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    @Modifying
    @Query("UPDATE loans SET effective_return_date = :date where loan_code=:id")
    int returnLoan(Long id, LocalDateTime date);
}
