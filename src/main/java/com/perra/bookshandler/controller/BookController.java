package com.perra.bookshandler.controller;

import java.util.ArrayList;
import java.util.List;

import com.perra.bookshandler.exception.RessourceNotFoundException;
import com.perra.bookshandler.model.Book;
import com.perra.bookshandler.openlibrary.OpenLibrary;
import com.perra.bookshandler.openlibrary.model.OLDataBook;
import com.perra.bookshandler.service.BookService;

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
	private BookService bookService;

	@Autowired
	private OpenLibrary openLibrary;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/books")
	public List<Book> all() {
		return this.bookService.findAll();
	}

	// Push a book
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/book")
	public Book saveBook(@RequestBody Book book) {
		// if (bookRepository.findByKey(book.getKey()) != null)
		// throw new RessourceAlreadyExistException("Book with key " + book.getKey() + "
		// already exists.");
		return this.bookService.saveBook(book);
	}

	// Update a book
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/book")
	public Book updateBook(@RequestBody Book book) {
		return this.bookService.saveBook(book);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/book/bibkey")
	public Book getByBibKey(
			@RequestParam(value = "requestOL", required = false, defaultValue = "false") String requestOL,
			@RequestParam(value = "bibkey", required = true) String bibkey,
			@RequestParam(value = "value", required = true) String value) {
		Book bookFromRepo = this.bookService.findByBibKeys(bibkey, value);

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
		List<Book> repoBooks = this.bookService.findByTitle(title);
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
}
