package com.resto.appService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resto.appService.service.AppService;
import com.resto.commonModel.entity.Person;
import com.resto.commonModel.response.Response;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

	@Autowired
	private AppService service;
	
	@PostMapping("/person")
	public Response<Person> addPerson(@RequestBody Person person){
		List<Person> result = new ArrayList<Person>();
		if(person.getPersonId() != null)
			result.add(service.updatePerson(person));
		else
			result.add(service.savePerson(person));
		return new Response<Person>("Person", result);
	}
	
	@GetMapping(value = "/person", params = {"page","size","status"})
	public Response<Person> getPersons(@RequestParam int page, @RequestParam int size, @RequestParam String status){
		return new Response<Person>("Person", service.getPersons(page,size,status));
	}
	
	@PutMapping("/person/activate/{personId}")
	public void activateUser(@PathVariable Long personId){
		service.activatePerson(personId);
	}
	
	@PutMapping("/person/deactivate/{personId}")
	public void deactivateUser(@PathVariable Long personId){
		service.deactivatePerson(personId);
	}
	
}
