package com.perra.bookshandler.openlibrary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OLAuthor {
    @Id
    private String id;

    // Data from openlibrary
    private String key;
    @JsonProperty("alternate_names")
    private List<String> alternateNames;
    //private String type;
    private String name;
    @JsonProperty("personal_name")
    private String personalName;
    @JsonProperty("birth_date")
    private String birthDate;
    private Object bio;
    
    private List<OLBook> books;

    public OLAuthor() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getAlternateNames() {
        return this.alternateNames;
    }

    public void setAlternateNames(List<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalName() {
        return this.personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public Object getBio() {
        return this.bio;
    }

    public void setBio(Object bio) {
        this.bio = bio;
    }

    public List<OLBook> getBooks() {
        return this.books;
    }

    public void setBooks(List<OLBook> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "OLAuthor: key: " + this.key + " name: " + this.name + " birthDate: "
                + this.birthDate;
    }
}
