package com.resto.appService.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resto.appService.dao.TaxDAO;
import com.resto.commonModel.entity.Tax;

@Repository
public class TaxDAOImpl implements TaxDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Tax saveTax(Tax tax) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(tax);
		return tax;
	}

	@Override
	public List<Tax> getAllTaxes() {
		Session session = entityManager.unwrap(Session.class);
		Query<Tax> query = session.createQuery("from Tax order by creationDate",Tax.class);
		return query.getResultList();
	}

	@Override
	public void deleteTax(Long taxId) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(session.get(Tax.class,taxId));
	}

}
