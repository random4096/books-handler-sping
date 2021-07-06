package com.perra.bookshandler.openlibrary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorSearch {
    private long numFound;
    private List<OLAuthor> docs;

    public AuthorSearch() {}

    public long getNumFound() {
        return this.numFound;
    }

    public void setNumFound(long numFound) {
        this.numFound = numFound;
    }

    public List<OLAuthor> getDocs() {
        return this.docs;
    }

    public void setDocs(List<OLAuthor> docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        String uuu = "authorSearch: numFound: " + this.numFound + "\n";
        for (int k = 0; k < this.docs.size(); k++) {
            uuu += "\t" + this.docs.get(k).toString() + "\n";
        }
        return uuu;
    }
}
