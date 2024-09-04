package com.example.library.controller;

import com.example.library.models.Book;
import com.example.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/library/books")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
    @GetMapping
    public Iterable<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/search")
    public ResponseEntity<Object> findBook(@RequestParam(name = "id", required = false) Long id,
                                   @RequestParam(name = "title", required = false) String title)
    {
        if(id != null) {
            Optional<Book> book = bookService.searchBook(id);
            return book.isPresent() ? ResponseEntity.ok().body(book) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("No book with this id found!");
        }
        return null;
    }
}
