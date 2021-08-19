package com.perra.bookshandler.controller;

import java.util.List;

import com.perra.bookshandler.model.Person;
import com.perra.bookshandler.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    
    @Autowired
    private PersonRepository personRepository;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/persons")
    public List<Person> all() {
        return this.personRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/person")
    public Person save(@RequestBody Person person) {
        return this.personRepository.save(person);
    }

    @CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/person")
    public Person update(@RequestBody Person person) {
        return this.personRepository.save(person);
    }

    @CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/persons")
    public String delete(@RequestBody Person person) {
        this.personRepository.delete(person);
        return "person removed";
    }    
}
