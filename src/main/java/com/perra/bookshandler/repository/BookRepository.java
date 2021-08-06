package com.perra.bookshandler.repository;

import java.util.List;

import com.perra.bookshandler.model.Book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByDataTitleContainingIgnoreCase(String title);

	@Query("{ 'data.bibkeys.?0': '?1' }")
    Book findByDataBibkeys(String key, String value);    

	//@Query("{ 'data.bibkeys.?0': '?1' }")
    //List<Book> findByDataBibkeysIn(List<String> key, List<String> value);    
}
