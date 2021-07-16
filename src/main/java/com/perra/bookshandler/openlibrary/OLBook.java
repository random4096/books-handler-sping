package com.perra.bookshandler.openlibrary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OLBook {

    private boolean read;
    private boolean owned;
    
    // Data from openlibrary
    private Object description;
    private String title;
    private String key;
    private String[] subjects;
    private String[] isbn_10;

    public OLBook() {}

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
        if (this.isbn_10 != null && this.isbn_10.length > 0) return this.isbn_10[0];
        return "";
    }

    public void setIsbn_10(String[] isbn_10) {
        this.isbn_10 = isbn_10;
    }

    @Override
    public String toString() {
        String s = "\nOLBook: title: " + this.title;
        s += " key: " + this.key;
        s += " isbn: " + this.isbn_10[0];
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
