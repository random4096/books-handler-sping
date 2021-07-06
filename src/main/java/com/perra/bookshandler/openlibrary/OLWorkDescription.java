package com.perra.bookshandler.openlibrary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OLWorkDescription {
    private String type;
    private String value;

    public OLWorkDescription() {}

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "desc: " + this.type + " " + this.value;
    }
}
