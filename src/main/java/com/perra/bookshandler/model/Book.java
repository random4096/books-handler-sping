package com.perra.bookshandler.model;

import com.perra.bookshandler.openlibrary.OLBook;

import org.springframework.data.annotation.Id;

public class Book {
    
    @Id
    private String id;
    private boolean read;
    private boolean owned;
    private String savedDate;
    
    // Data from openlibrary
    private Object description;
    private String title;
    private String isbn10;
    private String key;
    private String authorKey;

    public Book() {}

    public Book(OLBook book) {
        this.description = book.getDescription();
        this.title = book.getTitle();
        this.isbn10 = book.getIsbn_10();
        this.key = book.getKey();
        this.authorKey = book.getAuthorKey();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getRead() {
        return this.read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean getOwned() {
        return this.owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }




    // Data from openlibrary
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

    public String getIsbn10() {
        return this.isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAuthorKey() {
        return this.authorKey;
    }

    public void setAuthorKey(String authorKey) {
        this.authorKey = authorKey;
    }

    @Override
    public String toString() {
        String s = "\nBook: title: " + this.title;
        s += " isbn: " + this.isbn10+ " read " + this.read+ " owned " + this.owned;
        if (this.description != null) s += "\ndesc: " + this.description.toString();
        return s;
    }
}
