package com.perra.bookshandler.openlibrary.model;

public class OLIdentifier {
    private String name;
    private String value;

    public OLIdentifier() {}

    public OLIdentifier(String name, String value) {
        this.name = name;
        this.value = value;
    }

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name; 
	}

	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value; 
	}

    @Override
    public String toString() {
        return this.name + ":" + this.value;
    }
    
}
