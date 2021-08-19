package com.perra.bookshandler.service;

import java.util.List;

import com.perra.bookshandler.model.Book;
import com.perra.bookshandler.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		return this.bookRepository.findAll();
	}

	public Book saveBook(Book book) {
		if (book.getSavedDate() == null) book.setSavedDate(java.time.LocalDateTime.now().toString());
		return this.bookRepository.save(book);
	}
    
	public Book findByBibKeys(String bibKey, String value) {
		if (bibKey.equals("isbn")) bibKey += "_" + value.length();

		return this.bookRepository.findByDataBibkeys(bibKey, value);
	}

	public List<Book> findByTitle(String title) {
		return this.bookRepository.findByDataTitleContainingIgnoreCase(title);
	}

}
