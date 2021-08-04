package com.perra.bookshandler.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.perra.bookshandler.exception.RessourceNotFoundException;
import com.perra.bookshandler.model.Book;
import com.perra.bookshandler.model.BookTest;
import com.perra.bookshandler.openlibrary.OLBook;
import com.perra.bookshandler.openlibrary.OpenLibrary;
import com.perra.bookshandler.openlibrary.model.OLDataBook;
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
		// if (bookRepository.findByKey(book.getKey()) != null)
		// throw new RessourceAlreadyExistException("Book with key " + book.getKey() + "
		// already exists.");
		return bookRepository.save(book);
	}

	// Update a book
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/book")
	public Book updateBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/book/isbn")
	public List<Book> getByISBN(
			@RequestParam(value = "requestOL", required = false, defaultValue = "false") String requestOL,
			@RequestParam(value = "isbn") String isbn) {
		Book bookFromRepo = bookRepository.findByIsbn10(isbn);
		if (bookFromRepo == null) {
			if (requestOL.equals("false")) {
				throw new RessourceNotFoundException("Book with ISBN " + isbn + " not found.");
			}
			try {
				OLBook bookFromOL = openLibrary.findBookByISBN(isbn);
				bookFromRepo = new Book(bookFromOL);
			} catch (Exception e) {
				throw new RessourceNotFoundException("Book with ISBN " + isbn + " not found." + e.getMessage());
			}
		}
		return Arrays.asList(bookFromRepo);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/book/title")
	public List<Book> getByTitle(
			@RequestParam(value = "requestOL", required = false, defaultValue = "false") String requestOL,
			@RequestParam(value = "title") String title) {
		List<Book> repoBooks = this.bookRepository.findByTitleContainingIgnoreCase(title);
		if (!requestOL.equals("false")) {
			List<OLBook> olBooks = this.openLibrary.searchBooksbyTitle(title);
			List<Book> books = new ArrayList<Book>();
	
			for (int k = 0; k < olBooks.size(); k++) {
				Book book = null;
	
				for (int h = 0; h < repoBooks.size(); h++) {
					if (olBooks.get(k).getKey().equals(repoBooks.get(h).getKey())) {
						book = repoBooks.get(h);
						break;
					}
				}
	
				books.add(book != null ? book : new Book(olBooks.get(k)));
			}

			return books;
		}
		
		return repoBooks;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/books/search")
	public List<Book> get(@RequestParam(value = "requestOL", required = false, defaultValue = "false") String requestOL,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "isbn", required = false) String isbn) {
		// List<OLBook> books = this.openLibrary.searchBooksbyTitle(title);
		// TODO search in repos

		System.out.println("title" + title + " isn: " + isbn);
		/*
		 * Book book = new Book(); if (title != null) book.setTitle(title); if (isbn !=
		 * null) book.setIsbn10(isbn);
		 */
		// title = title == null ? "" : title;

		isbn = isbn == null ? "" : isbn;
		List<Book> books = // this.bookRepository.findAll(Example.of(book));
				this.bookRepository.findByTitleOrIsbn10(title, isbn);
		return books;
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/books/test")
	public BookTest get(@RequestParam(value = "isbn", required = false) String isbn) {
		BookTest test = new BookTest(this.openLibrary.findBookByISBNAPI(isbn));
		System.out.println(test.toString());
		return test;

	}
}
