package com.perra.bookshandler.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.perra.bookshandler.exception.RessourceNotFoundException;
import com.perra.bookshandler.model.Book;
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
		book.setSavedDate(java.time.LocalDateTime.now().toString());
		return bookRepository.save(book);
	}

	// Update a book
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/book")
	public Book updateBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/book/bibkey")
	public Book getByBibKey(
			@RequestParam(value = "requestOL", required = false, defaultValue = "false") String requestOL,
			@RequestParam(value = "bibkey", required = true) String bibkey,
			@RequestParam(value = "value", required = true) String value) {

		if (bibkey.equals("isbn")) bibkey += "_" + value.length();

		Book bookFromRepo = bookRepository.findByDataBibkeys(bibkey, value);

		if (bookFromRepo == null) {
			if (requestOL.equals("false")) {
				throw new RessourceNotFoundException("Book with " + bibkey + " " + value + " not found.");
			}
			try {
				OLDataBook bookFromOL = openLibrary.findBookByBibKeys(bibkey, value);
				bookFromRepo = new Book(bookFromOL);
			} catch (Exception e) {
				throw new RessourceNotFoundException("Book with " + bibkey + " " + value + " not found. " + e.getMessage());
			}
		}
		return bookFromRepo;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/book/title")
	public List<Book> getByTitle(
			@RequestParam(value = "requestOL", required = false, defaultValue = "false") String requestOL,
			@RequestParam(value = "title") String title) {
		List<Book> repoBooks = this.bookRepository.findByDataTitleContainingIgnoreCase(title);
		if (!requestOL.equals("false")) {
			List<OLDataBook> olBooks = this.openLibrary.searchBooksbyTitle(title);
			List<Book> books = new ArrayList<Book>();
	
			for (int k = 0; k < olBooks.size(); k++) {
				Book book = null;
	
				for (int h = 0; h < repoBooks.size(); h++) {
					if (olBooks.get(k).getKey().equals(repoBooks.get(h).getData().getKey())) {
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
	@GetMapping("/books/test")
	public Book get(@RequestParam(value = "isbn", required = false) String isbn) {
		Book test = new Book(this.openLibrary.findBookByISBNAPI(isbn));
		System.out.println(test.toString());
		return test;

	}
}
