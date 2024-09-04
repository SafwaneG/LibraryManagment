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
    public Book borrowBook(Book book) {
        if(isBookAvailable(book.getBookTitle())) {
            book.setAvailableSamples(book.getAvailableSamples() - 1);
            return bookRepository.save(book);
        }
        return null;
    }

    public void returnBook(Long bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent()) {
            Book book1 = book.get();
            if(book1.getAvailableSamples() < book1.getSamplesTotalNumber()) {
                book1.setAvailableSamples(book1.getAvailableSamples() + 1);
                bookRepository.save(book1);
            }
        }

    }
}
