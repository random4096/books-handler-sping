package com.perra.bookshandler.service;

import java.util.Arrays;
import java.util.List;

import com.perra.bookshandler.exception.RessourceNotFoundException;
import com.perra.bookshandler.model.Book;
import com.perra.bookshandler.openlibrary.OLBook;
import com.perra.bookshandler.openlibrary.OpenLibrary;
import com.perra.bookshandler.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
    
	@Autowired
	private OpenLibrary openLibrary;

    public List<Book> getByISBN(String requestOL, String isbn) {
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
}
