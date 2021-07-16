package com.perra.bookshandler.openlibrary;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenLibrary {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://openlibrary.org";

    // AUTHORS
    public List<OLAuthor> findAuthorsByName(String name) {
        AuthorSearch authorSearch = restTemplate.getForObject(baseUrl + "/search/authors.json?q=" + name,
                AuthorSearch.class);
        return authorSearch.getDocs();
    }

    public List<OLBook> findAuthorWorksByAuthorKey(String key) {
        WorksByAuthorKey worksByAuthorKey = restTemplate.getForObject(baseUrl + "/authors/" + key + "/works.json",
                WorksByAuthorKey.class);
        return worksByAuthorKey.getEntries();
    }

    public OLAuthor findAuthorByKey(String key) {
        OLAuthor author = restTemplate.getForObject(baseUrl + "/authors/" + key + ".json", OLAuthor.class);
        author.setKey(key);
        return author;
    }

    // BOOKS
    public OLBook findBookByISBN(String isbn) {
        OLBook book = restTemplate.getForObject(baseUrl + "/isbn/" + isbn + ".json", OLBook.class);
        return book;
    }

    @SuppressWarnings("unchecked") // TODO not pretty
    public List<OLBook> searchBooksbyTitle(String title) {
        
        List<OLBook> books = (List<OLBook>)(Object)
            (restTemplate.getForObject(baseUrl + "/search.json?title=" + title, Search.class).getDocs());
        return books;
    }
}
