package com.resto.appService.dao;

import java.util.List;

import com.resto.commonModel.entity.Person;

public interface PersonDAO {
	public List<Person> getPersons();
	public Person savePerson(Person person);
	public Person deactivatePerson(Person person);
}
