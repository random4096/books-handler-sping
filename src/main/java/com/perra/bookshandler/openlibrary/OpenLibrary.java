package com.perra.bookshandler.openlibrary;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenLibrary {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://openlibrary.org";

    public AuthorSearch findAuthorByName(String name) {
        AuthorSearch authorSearch = restTemplate.getForObject(baseUrl + "/search/authors.json?q=" + name,
                AuthorSearch.class);
        return authorSearch;
    }

    public WorksByAuthorKey findAuthorWorksByAuthorKey(String key) {
        WorksByAuthorKey worksByAuthorKey = restTemplate.getForObject(baseUrl + "/authors/" + key + "/works.json",
                WorksByAuthorKey.class);
        return worksByAuthorKey;
    }

    public OLAuthor findAuthorByKey(String key) {
        OLAuthor author = restTemplate.getForObject(baseUrl + "/authors/" + key + ".json", OLAuthor.class);
        author.setKey(key);
        return author;
    }
}
