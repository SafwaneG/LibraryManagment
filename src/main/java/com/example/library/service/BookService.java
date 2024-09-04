package com.example.library.service;

import com.example.library.models.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> searchBook(Long id) {
        return bookRepository.findById(id);
    }
    public boolean isBookAvailable(String title){
        Optional<Book> book = bookRepository.findBookByBookTitle(title);
        return book.map(book1 -> book1.getAvailableSamples() > 0).orElse(false);
    }
    public int borrowBook(Book book) {
        if(isBookAvailable(book.getBookTitle())) {
            return bookRepository.borrowBook(book.getBookCode(), book.getAvailableSamples() -1);
        }
        return 0;
    }

    public void returnBook(Long bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        book.ifPresent(book1 -> {
            if(book1.getAvailableSamples() < book1.getSamplesTotalNumber()) {
                bookRepository.returnBook(book1.getAvailableSamples()+1, bookId);
            }
        });
    }
}
