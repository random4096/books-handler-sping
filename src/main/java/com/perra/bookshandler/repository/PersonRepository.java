package com.perra.bookshandler.repository;

import com.perra.bookshandler.model.Person;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
}
