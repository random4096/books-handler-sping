package com.perra.bookshandler.openlibrary.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * for json object of form
 * {
 *      "xxx": value
 * }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Wrapper<T> {
    private Map<String, T> data = new HashMap<String, T>();

    public Wrapper() {
    }

    @JsonAnySetter
    public void set(String code, T data) {
        this.data.put(code, data);
    }

    public Map<String, T> getData() {
        return this.data;
    }

}