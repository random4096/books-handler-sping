package com.perra.bookshandler.openlibrary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Search {
    private List<Object> docs;

    public Search() {}

    public List<Object> getDocs() {
        return this.docs;
    }

    public void setDocs(List<Object> docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        String uuu = "Search: numFound: " + this.docs.size() + "\n";
        for (int k = 0; k < this.docs.size(); k++) {
            uuu += "\t" + this.docs.get(k).toString() + "\n";
        }
        return uuu;
    }
}
