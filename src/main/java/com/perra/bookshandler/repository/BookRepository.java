package com.perra.bookshandler.repository;

import com.perra.bookshandler.model.Book;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
