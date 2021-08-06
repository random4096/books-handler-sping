package com.perra.bookshandler.openlibrary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.perra.bookshandler.openlibrary.model.OLDataBook;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorksByAuthorKey {
    private List<OLDataBook> entries;

    public WorksByAuthorKey() {}

    public List<OLDataBook> getEntries() {
        return this.entries;
    }

    public void setEntries(List<OLDataBook> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        String s = "WorksByAuthorKey nWork" + this.entries.size() + "\n";
        for (int k = 0; k < this.entries.size(); k++) {
            s += this.entries.get(k).toString() + "\n";
        }
        return s;
    }
}
