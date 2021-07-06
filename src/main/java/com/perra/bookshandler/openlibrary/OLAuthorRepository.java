package com.perra.bookshandler.openlibrary;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OLAuthorRepository extends MongoRepository<OLAuthor, String> {
    public OLAuthor findByKey(String key);
    public OLAuthor findByName(String name);
}
