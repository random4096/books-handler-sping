package com.perra.bookshandler.openlibrary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OLBook {

    //private String description;
    private Object description;
    private String title;
    private String key;
    private String[] subjects;

    public OLBook() {}

    /*public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }*/

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

    @Override
    public String toString() {
        String s = "OLBook: title: " + this.title;
        s += " key: " + this.key;
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
