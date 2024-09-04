package com.example.library.controller;

import com.example.library.models.Loan;
import com.example.library.models.LoanState;
import com.example.library.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/library/loans")
public class LoanController {
    private final LoanService loanService;
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public Loan saveLoan(@RequestBody Loan loan) {
        return loanService.addLoan(loan);
    }
    @GetMapping
    public Iterable<Loan> findAllLoans() {
        return loanService.findAllLoans();
    }

    @GetMapping("/search")
    public ResponseEntity<Object> findLoan(@RequestParam(name = "id") Long id)
    {
        if(id != null) {
            Optional<Loan> loan = loanService.searchLoan(id);
            return loan.isPresent() ? ResponseEntity.ok().body(loan) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("No loan with this id found!");
        }
        return null;
    }
    @PutMapping("/{id}")
    public int returnLoan(@PathVariable("id") Long id) {
        return loanService.returnLoan(id);
    }
    @GetMapping("/state/{id}")
    public LoanState loanState(@PathVariable Long id) {
        return loanService.loanState(id);
    }
}
