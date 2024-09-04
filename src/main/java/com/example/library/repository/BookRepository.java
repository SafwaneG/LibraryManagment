package com.example.library.repository;

import com.example.library.models.Book;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book,Long > {
    Optional<Book> findBookByBookTitle(String title);
    @Modifying
    @Query("UPDATE books set available_samples=:availableSamples where book_code=:id")
    void returnBook(int availableSamples, Long id);
    @Modifying
    @Query("UPDATE books set available_samples=:availableSamples where book_code=:id")
    int borrowBook(Long id, int availableSamples);
}
