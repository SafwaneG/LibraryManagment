package com.example.library.ControllerAdvise;

import com.example.library.Exceptions.BookNotAvailableException;
import com.example.library.Exceptions.LoanAlreadyClosed;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice(basePackages = "com.example.library.controller")
public class LibraryControllerAdvice {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String sqlConstraintExceptionHandler() {
        return "There is no author with this id.";
    }

    @ExceptionHandler(BookNotAvailableException.class)
    public String BookNotFoundHandler() {
        return "This book is not available.";
    }
    @ExceptionHandler(LoanAlreadyClosed.class)
    public String loanClosedHandler(){
        return "This loan is already closed!";
    }
}
