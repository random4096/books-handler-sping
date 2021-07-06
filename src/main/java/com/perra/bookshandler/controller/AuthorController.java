package com.perra.bookshandler.controller;

import java.util.List;

import com.perra.bookshandler.model.Author;
import com.perra.bookshandler.openlibrary.AuthorSearch;
import com.perra.bookshandler.openlibrary.OLAuthor;
import com.perra.bookshandler.openlibrary.OLAuthorRepository;
import com.perra.bookshandler.openlibrary.OpenLibrary;
import com.perra.bookshandler.openlibrary.WorksByAuthorKey;
import com.perra.bookshandler.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

	@Autowired
	private OLAuthorRepository authorRepository;

	@Autowired
	private OpenLibrary openLibrary;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/authors")
	public List<OLAuthor> all() {
		return authorRepository.findAll();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/author")
	public OLAuthor findAuthor(@RequestParam(value = "key") String key) {
		OLAuthor author = authorRepository.findByKey(key);
		return author;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/author")
	public OLAuthor saveAuthor(@RequestParam(value = "key") String key) {
		OLAuthor savedAuthor = authorRepository.findByKey(key);
		OLAuthor author = openLibrary.findAuthorByKey(key);
		author.setBooks(openLibrary.findAuthorWorksByAuthorKey(key));

		if (savedAuthor != null)
			author.setId(savedAuthor.getId());

		return authorRepository.save(author);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/author")
	public OLAuthor updateAuthor(@RequestBody OLAuthor author) {
		return authorRepository.save(author);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/search/authors")
	public List<OLAuthor> searchAuthors(@RequestParam(value = "name") String name) {
		// TODO
		// search in DB
		// fusionner
		System.out.println("laaa" + name);
		return openLibrary.findAuthorsByName(name);
	}
}