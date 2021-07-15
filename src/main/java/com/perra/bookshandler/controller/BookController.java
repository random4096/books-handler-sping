package com.perra.bookshandler.controller;

import java.util.List;

import com.perra.bookshandler.model.Book;
import com.perra.bookshandler.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/books")
	public List<Book> all() {
		return bookRepository.findAll();
	}
	
	// Push a book
    @CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/book")
	public Book saveBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	// Update a book
    @CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/book")
	public Book updateBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}
    
}
