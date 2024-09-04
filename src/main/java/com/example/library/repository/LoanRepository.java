package com.example.library.repository;

import com.example.library.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
