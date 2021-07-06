package com.perra.bookshandler.openlibrary;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OLAuthorRepository extends MongoRepository<OLAuthor, String> {
    public OLAuthor findByKey(String key);
    public OLAuthor findByName(String name);
    public List<OLAuthor> findByKeyIn(List<String> keyLisy);
}
