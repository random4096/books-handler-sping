package com.perra.bookshandler.model;

import com.perra.bookshandler.openlibrary.OLBook;

import org.springframework.data.annotation.Id;

public class Book {
    
    @Id
    private String id;
    private boolean read;
    private boolean owned;
    
    // Data from openlibrary
    private Object description;
    private String title;
    private String isbn10;

    public Book() {}

    public Book(OLBook book) {
        this.description = book.getDescription();
        this.title = book.getTitle();
        this.isbn10 = book.getIsbn_10();
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

    @Override
    public String toString() {
        String s = "\nBook: title: " + this.title;
        s += " isbn: " + this.isbn10;
        if (this.description != null) s += "\ndesc: " + this.description.toString();
        return s;
    }
}
