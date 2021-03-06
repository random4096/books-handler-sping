package com.perra.bookshandler.model;

import org.springframework.data.annotation.Id;

public class Person {
    
    @Id
    private String id;
    private String name;

    public Person() {}

	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id; 
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name; 
	}
}
