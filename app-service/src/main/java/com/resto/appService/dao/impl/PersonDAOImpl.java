package com.resto.appService.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.resto.appService.dao.PersonDAO;
import com.resto.commonModel.entity.Person;
import com.resto.commonModel.entity.Role;
import com.resto.commonModel.entity.User;
import com.resto.commonModel.exception.UserAlreadyExistsException;

@Repository
public class PersonDAOImpl implements PersonDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Person> getPersons() {
		Session session = entityManager.unwrap(Session.class);
		Query<Person> query = session.createQuery("from Person",Person.class);
		return query.getResultList();
	}

	@Override
	public Person savePerson(Person person) {
		if(checkPhoneNumberExists(person.getPhoneNumber())) {
			throw new UserAlreadyExistsException("Phone number already exists. Please try with a different phone number");
		}
		Session session = entityManager.unwrap(Session.class);
		User user = new User();
		user.setEnabled(true);
		user.setUsername(getUniqueUsername(person.getFullName()));
		user.setPassword(generateDefaultPassword());
		session.save(user);
		Role role = new Role();
		role.setRole("ROLE_EMPLOYEE");
		role.setUser(user);
		session.save(role);
		person.setUser(user);
		person.setRole(role);
		session.saveOrUpdate(person);
		return person;
	}

	@Override
	public Person deactivatePerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getUniqueUsername(String token) {
		String username = "";
		token = token.trim();
		if(token.indexOf(" ") > -1) {
			token = token.substring(0,token.indexOf(" ") - 1);
		}
		int count = 0;
		while(checkUserExists(username)) {
			count++;
			username = token + count;
		}
		return username;
	}

	private boolean checkPhoneNumberExists(String phoneNumber) {
		Session session = entityManager.unwrap(Session.class);
		Query<Person> query = session.createQuery("from Person where phoneNumber=:pPhoneNumber", Person.class);
		query.setParameter("pPhoneNumber", phoneNumber);
		return query.getResultList().size() > 0;
	}
	
	private boolean checkUserExists(String username) {
		Session session = entityManager.unwrap(Session.class);
		Query<User> query = session.createQuery("from User where username=:pUsername", User.class);
		query.setParameter("pUsername", username);
		return query.getResultList().size() > 0;
	}
	
	private String generateDefaultPassword() {
		String password = "test123";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return "{bcrypt}" + hashedPassword;
	}

}
