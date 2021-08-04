package com.perra.bookshandler.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.perra.bookshandler.openlibrary.model.OLDataBook;

import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookTest {

    @Id
    private String id;
    private boolean read;
    private boolean owned;
    private String savedDate;
    private OLDataBook data;
    
    public BookTest() {
        super();
    }

    public BookTest(OLDataBook book) {
        this.data = book;
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

	public String getSavedDate() {
		return this.savedDate;
	}
	
	public void setSavedDate(String savedDate) {
		this.savedDate = savedDate; 
	}

	public OLDataBook getData() {
		return this.data;
	}
	
	public void setData(OLDataBook data) {
		this.data = data; 
	}

    @Override
    public String toString() {
        String str = "id:" + this.id + "read: " + this.read + " owned: " 
        + this.owned + " savedDate: " + this.savedDate
        + "\n" + this.data != null ? this.data.toString() : "No data.";
        return str;
    }

}
