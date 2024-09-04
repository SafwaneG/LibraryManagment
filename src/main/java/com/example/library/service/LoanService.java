package com.example.library.service;

import com.example.library.Exceptions.BookNotAvailableException;
import com.example.library.Exceptions.LoanAlreadyClosed;
import com.example.library.models.Book;
import com.example.library.models.Loan;
import com.example.library.models.LoanState;
import com.example.library.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookService bookService;
    public LoanService(LoanRepository loanRepository, BookService bookService) {
        this.loanRepository = loanRepository;
        this.bookService = bookService;
    }

    public LoanState loanState(Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        return loan.map(loan1 -> {
            LocalDateTime todayDate = LocalDateTime.now();
            LocalDateTime effectiveReturnDate = loan1.getEffectiveReturnDate();
            LocalDateTime expectedReturnDate = loan1.getExpectedReturnDate();
            if(effectiveReturnDate == null) {
                return todayDate.isBefore(expectedReturnDate) ? LoanState.ONPROGRESS : LoanState.NOT_RETURNED;
            }else{
                return effectiveReturnDate.isAfter(expectedReturnDate) ? LoanState.RETURNED_WITH_DELAY : LoanState.RETURNED_ON_TIME;
            }}
        ).orElse(null);
    }
    @Transactional
    public int returnLoan(Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        if(loan.isPresent() && loan.get().getEffectiveReturnDate() == null) {
            bookService.returnBook(loan.get().getBorrowedBookId());
            return loanRepository.returnLoan(id, LocalDateTime.now());
        }
        else{
            throw new LoanAlreadyClosed();
        }
    }

    public Loan addLoan(Loan loan) {
        Long requestedBookId = loan.getBorrowedBookId();
        Optional<Book> requestedBook = bookService.searchBook(requestedBookId);
        requestedBook.map(requestedBook1 -> {
            if(bookService.borrowBook(requestedBook1) == 1) {
                return loanRepository.save(loan);
            }else {
                throw new BookNotAvailableException();
            }
        });
        return null;
    }

    public Iterable<Loan> findAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> searchLoan(Long id) {
        return loanRepository.findById(id);
    }
}
