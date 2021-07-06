package com.perra.bookshandler.repository;

import com.perra.bookshandler.model.Author;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
    
    public Author findByName(String name);
}