package com.example.library.repository;

import com.example.library.models.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long > {
    Optional<Book> findBookByBookTitle(String title);

}
