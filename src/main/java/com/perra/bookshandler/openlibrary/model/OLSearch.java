package com.perra.bookshandler.openlibrary.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OLSearch<T> {
    private List<T> docs;

    public OLSearch() {}

    public List<T> getDocs() {
        return this.docs;
    }

    public void setDocs(List<T> docs) {
        this.docs = docs;
    }
}
