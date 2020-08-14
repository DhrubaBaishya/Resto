package com.resto.appService.dao;

import java.util.List;

import com.resto.commonModel.entity.Person;

public interface PersonDAO {
	public List<Person> getPersons(int page, int size, String status);
	public Person savePerson(Person person);
	public Person updatePerson(Person person);
	public void deactivatePerson(Long personId);
	public void activatePerson(Long personId);
}
