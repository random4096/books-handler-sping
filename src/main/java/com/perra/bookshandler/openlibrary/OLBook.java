package com.perra.bookshandler.openlibrary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

class AuthorKey {
    private String key;
    public AuthorKey() {};

    public String getKey() { return this.key; }
    public void setKey(String key) { this.key = key; }
}

@JsonIgnoreProperties(ignoreUnknown = true)
public class OLBook {

    private Object description;
    private String title;
    private String key;
    private String[] subjects;
    private String isbn_10;
    @JsonProperty("authors")
    private String authorKey;

    public OLBook() {}

    public Object getDescription() {
        return this.description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getSubjects() {
        return this.subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    public String getIsbn_10() {
        return this.isbn_10;
    }

    public void setIsbn_10(String[] isbn_10) {
        if (isbn_10 != null && isbn_10.length > 0) this.isbn_10 = isbn_10[0];
        else this.isbn_10 = "";
    }

    public String getAuthorKey() {
        return this.authorKey;
    }

    public void setAuthorKey(AuthorKey[] authorKey) {
        if (authorKey != null && authorKey.length > 0) this.authorKey = authorKey[0].getKey();
        else this.authorKey = "";
    }

    @Override
    public String toString() {
        String s = "\nOLBook: title: " + this.title;
        s += " key: " + this.key + " athorkey:" + authorKey;
        //s += " isbn: " + this.isbn_10[0];
        if (this.subjects != null && this.subjects.length > 0) {
            s += "\nsubjects: ";
            for (int k = 0; k < this.subjects.length; k++) {
                s += this.subjects[k] + " ";
            }
        }
        if (this.description != null) s += "\ndesc: " + this.description.toString();
        return s;
    }
    
}
 