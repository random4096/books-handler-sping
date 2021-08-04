package com.perra.bookshandler.openlibrary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.perra.bookshandler.exception.RessourceNotFoundException;
import com.perra.bookshandler.openlibrary.model.OLDataBook;
import com.perra.bookshandler.openlibrary.model.OLSearch;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@JsonIgnoreProperties(ignoreUnknown = true)
class Wrapper<T> {
    private Map<String, T> data = new HashMap<String, T>();

    public Wrapper() {
    }

    @JsonAnySetter
    public void set(String code, T data) {
        this.data.put(code, data);
    }

    public Map<String, T> getData() {
        return this.data;
    }

}

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

    public List<OLBook> searchBooksbyTitle(String title) {
        List<OLBook> books = restTemplate.exchange(
            baseUrl + "/search.json?title=" + title,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<OLSearch<OLBook>>() {}
        ).getBody().getDocs();
        
        return books;
    }

    public List<OLBook> searchBook(Map<String, String> fields) {

        return null;
    }

    // API
    
    public OLDataBook findBookByISBNAPI(String isbn) {
        Wrapper<OLDataBook> book = restTemplate.exchange(
            baseUrl + "/api/books?format=json&jscmd=data&bibkeys=ISBN:" + isbn + ",LCCN:93005405",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Wrapper<OLDataBook>>() {}
        ).getBody();
        
        for (Map.Entry<String,OLDataBook> entry : book.getData().entrySet()) {
            int i = entry.getKey().indexOf(':');
            if (isbn.equals(entry.getKey().substring(i + 1))) return entry.getValue();
        }

        throw new RessourceNotFoundException("isbn " + isbn + " not found.");
    }
}
