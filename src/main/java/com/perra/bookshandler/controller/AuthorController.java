package com.perra.bookshandler.controller;

import java.util.ArrayList;
import java.util.List;

import com.perra.bookshandler.openlibrary.OLAuthor;
import com.perra.bookshandler.openlibrary.OLAuthorRepository;
import com.perra.bookshandler.openlibrary.OpenLibrary;

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
		author.setSaved(true);

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
		List<OLAuthor> authors = openLibrary.findAuthorsByName(name);
		List<String> keys = new ArrayList<String>();
		for (int k = 0; k < authors.size(); k++) {
			keys.add(authors.get(k).getKey());
		}

		List<OLAuthor> authorsSaved = this.authorRepository.findByKeyIn(keys);

		// TODO
		for (OLAuthor authorSaved : authorsSaved) {
			if (authorSaved.getSaved()) {
				for (OLAuthor author : authors) {
					if (authorSaved.getKey().equals(author.getKey())){
						author.setSaved(true);
						break;
					}
				}
			} 
		}
		
		return authors;
	}
}