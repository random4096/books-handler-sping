package com.perra.bookshandler.model;

import org.springframework.data.annotation.Id;

@SuppressWarnings("all") // TODO not pretty
public class Author {

    @Id
    private String id;
    private boolean read;
    private boolean owned;

    // Data from openlibrary
    private String name;
    private String personalName;
    private String birthDate;
    private Object bio;


    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    } 

    public boolean getRead() {
        return this.read;
    }

    public boolean getOwned() {
        return this.owned;
    }

    @Override
    public String toString() {
        return "author: id: " + this.id + " name: " + this.name;
    }

}
