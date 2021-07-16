package com.perra.bookshandler.controller;

import java.util.List;

import com.perra.bookshandler.exception.RessourceNotFoundException;
import com.perra.bookshandler.model.Book;
import com.perra.bookshandler.openlibrary.OLBook;
import com.perra.bookshandler.openlibrary.OpenLibrary;
import com.perra.bookshandler.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private OpenLibrary openLibrary;

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

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/book/ISBN")
	public Book getByISBN(@RequestParam(value = "isbn") String isbn) {
		Book bookFromRepo = bookRepository.findByIsbn10(isbn);
		if (bookFromRepo == null) {
			try {
				OLBook bookFromOL = openLibrary.findBookByISBN(isbn);
				bookFromRepo = new Book(bookFromOL);
			} catch (Exception e) {
				throw new RessourceNotFoundException("Book with ISBN " + isbn + " not found.");
			}
		}
		return bookFromRepo;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/book/title")
	public List<OLBook> getByTitle(@RequestParam(value = "title") String title) {
		List<OLBook> books = this.openLibrary.searchBooksbyTitle(title);
		// TODO search in repos
		return books;
	}
}
