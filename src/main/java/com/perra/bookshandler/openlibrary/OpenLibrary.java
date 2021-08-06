package com.perra.bookshandler.openlibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.perra.bookshandler.exception.RessourceNotFoundException;
import com.perra.bookshandler.openlibrary.model.OLDataBook;
import com.perra.bookshandler.openlibrary.model.OLSearch;
import com.perra.bookshandler.openlibrary.model.Wrapper;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenLibrary {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "https://openlibrary.org";

    /*// AUTHORS
    public List<OLAuthor> findAuthorsByName(String name) {
        AuthorSearch authorSearch = restTemplate.getForObject(baseUrl + "/search/authors.json?q=" + name,
                AuthorSearch.class);
        return authorSearch.getDocs();
    }

    public List<OLDataBook> findAuthorWorksByAuthorKey(String key) {
        WorksByAuthorKey worksByAuthorKey = restTemplate.getForObject(baseUrl + "/authors/" + key + "/works.json",
                WorksByAuthorKey.class);
        return worksByAuthorKey.getEntries();
    }

    public OLAuthor findAuthorByKey(String key) {
        OLAuthor author = restTemplate.getForObject(baseUrl + "/authors/" + key + ".json", OLAuthor.class);
        author.setKey(key);
        return author;
    }*/

    // BOOKS
    public OLDataBook findBookByISBN(String isbn) {
        OLDataBook book = restTemplate.getForObject(baseUrl + "/isbn/" + isbn + ".json", OLDataBook.class);
        return book;
    }

    public List<OLDataBook> searchBooksbyTitle(String title) {
        List<OLBook> olBooks = restTemplate.exchange(
            baseUrl + "/search.json?title=" + title,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<OLSearch<OLBook>>() {}
        ).getBody().getDocs();
        
        String str = "";
        List<OLDataBook> noIdentifiersBooks = new ArrayList<OLDataBook>();
        for (OLBook b : olBooks) {

            if (!str.equals("")) str += ",";
            if (b.getIsbn_10() != null && !b.getIsbn_10().equals("")) {
                str += "ISBN:" + b.getIsbn_10();
            }
            else if(b.getLccn() != null && !b.getLccn().equals("")) {
                str += "LCCN:" + b.getLccn();
            }
            /*else if(b.getOclc() != null && !b.getOclc().equals("")) {
                str += "OCLC:" + b.getOclc();
            }*/
            else if(b.getOlid() != null && !b.getOlid().equals("")) {
                str += "OLID:" + b.getOlid();
            }
            else {
                // No identifiers
                noIdentifiersBooks.add(new OLDataBook(b));
            }
        }
        
        Map<String, OLDataBook> books = restTemplate.exchange(
            baseUrl + "/api/books?format=json&jscmd=data&bibkeys=" + str,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Wrapper<OLDataBook>>() {}
        ).getBody().getData();

        List<OLDataBook> res = new ArrayList<OLDataBook>(books.values());
        res.addAll(noIdentifiersBooks);
        return res;
    }

    // API
    public OLDataBook findBookByISBNAPI(String isbn) {
        Wrapper<OLDataBook> book = restTemplate.exchange(
            baseUrl + "/api/books?format=json&jscmd=data&bibkeys=ISBN:" + isbn,
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

    public OLDataBook findBookByBibKeys(String bibkey, String value) {
        Wrapper<OLDataBook> book = restTemplate.exchange(
            baseUrl + "/api/books?format=json&jscmd=data&bibkeys=" + bibkey.toUpperCase() + ":" + value,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Wrapper<OLDataBook>>() {}
        ).getBody();

        for (Map.Entry<String,OLDataBook> entry : book.getData().entrySet()) {
            int i = entry.getKey().indexOf(':');
            if (value.equals(entry.getKey().substring(i + 1))) return entry.getValue();
        }

        throw new RessourceNotFoundException(bibkey + " " + value + " not found.");
    }
}
