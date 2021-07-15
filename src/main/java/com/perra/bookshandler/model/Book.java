package com.perra.bookshandler.model;

import org.springframework.data.annotation.Id;

public class Book {
    
    @Id
    private String id;
    private boolean read;
    private boolean owned;
    
    // Data from openlibrary
    private Object description;
    private String title;

    public Book() {}

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

    @Override
    public String toString() {
        String s = "\nBook: title: " + this.title;
        if (this.description != null) s += "\ndesc: " + this.description.toString();
        return s;
    }
}
