package com.perra.bookshandler.repository;

import java.util.List;

import com.perra.bookshandler.model.Book;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
    Book findByKey(String key);
    Book findByIsbn10(String isbn10);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByTitleOrIsbn10(String title, String isbn10);
}
