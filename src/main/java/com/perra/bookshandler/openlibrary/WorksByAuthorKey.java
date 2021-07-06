package com.perra.bookshandler.openlibrary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorksByAuthorKey {
    private List<OLBook> entries;

    public WorksByAuthorKey() {}

    public List<OLBook> getEntries() {
        return this.entries;
    }

    public void setEntries(List<OLBook> entries) {
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
