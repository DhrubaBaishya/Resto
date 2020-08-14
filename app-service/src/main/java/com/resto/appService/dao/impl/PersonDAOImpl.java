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
	public List<Person> getPersons(int page, int size, String status) {
		Session session = entityManager.unwrap(Session.class);
		boolean enabled = "Active".equals(status) ? true : false;
		Query<Person> query = session.createQuery("from Person p where p.user.enabled = :pEnabled order by p.lastUpdateDate desc",Person.class);
		query.setParameter("pEnabled", enabled);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		return query.getResultList();
	}

	@Override
	public Person savePerson(Person person) {
		if(checkPhoneNumberExists(person)) {
			throw new UserAlreadyExistsException("Phone number already exists. Please try with a different phone number");
		}
		Session session = entityManager.unwrap(Session.class);
		User user = new User();
		user.setEnabled(true);
		user.setUsername(person.getPhoneNumber());
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
	public Person updatePerson(Person person) {
		Session session = entityManager.unwrap(Session.class);
		Person pPerson = session.get(Person.class, person.getPersonId());
		if(checkPhoneNumberExists(person)) {
			throw new UserAlreadyExistsException("Phone number already exists. Please try with a different phone number");
		}
		pPerson.setFullName(person.getFullName());
		if(!(pPerson.getPhoneNumber()).equals(person.getPhoneNumber()) && person.getPhoneNumber() != null) {
			User user = pPerson.getUser();
			user.setUsername(person.getPhoneNumber());;
			session.saveOrUpdate(user);
			pPerson.setPhoneNumber(person.getPhoneNumber());
		}
		session.saveOrUpdate(pPerson);
		return pPerson;
	}

	@Override
	public void deactivatePerson(Long personId) {
		Session session = entityManager.unwrap(Session.class);
		Person person = session.get(Person.class, personId);
		User user = person.getUser();
		user.setEnabled(false);
		session.saveOrUpdate(user);
	}

	@Override
	public void activatePerson(Long personId) {
		Session session = entityManager.unwrap(Session.class);
		Person person = session.get(Person.class, personId);
		User user = person.getUser();
		user.setEnabled(true);
		session.saveOrUpdate(user);
	}

	private boolean checkPhoneNumberExists(Person person) {
		Session session = entityManager.unwrap(Session.class);
		Query<Person> query = null;
		if(person.getPersonId() != null) {
			query = session.createQuery("from Person where phoneNumber=:pPhoneNumber and personId<>:pPersonId", Person.class);
			query.setParameter("pPersonId", person.getPersonId());
		}
		else {
			query = session.createQuery("from Person where phoneNumber=:pPhoneNumber ", Person.class);
		}
		query.setParameter("pPhoneNumber", person.getPhoneNumber());
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
