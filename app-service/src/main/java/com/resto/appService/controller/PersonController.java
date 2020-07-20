package com.resto.appService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
		result.add(service.savePerson(person));
		return new Response<Person>("Person", result);
	}
	
}
