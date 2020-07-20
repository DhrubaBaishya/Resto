package com.resto.appService.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resto.appService.dao.TableDAO;
import com.resto.commonModel.entity.Tables;

@Repository
public class TableDAOImpl implements TableDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Tables> getAllTables() {
		Session session = entityManager.unwrap(Session.class);
		Query<Tables> query = session.createQuery("from Tables where enabled = 1",Tables.class);
		return query.getResultList();
	}

	@Override
	public Tables getTable(int id) {
		Session session = entityManager.unwrap(Session.class);
		return session.get(Tables.class, id);
	}

	@Override
	public Tables addTable(Tables table) {
		Session session = entityManager.unwrap(Session.class);
		table.setEnabled(true);
		session.saveOrUpdate(table);
		return table;
	}

	@Override
	public void deleteTable(int id) {
		Session session = entityManager.unwrap(Session.class);
		Tables table = session.get(Tables.class, id);
		table.setEnabled(false);
		session.saveOrUpdate(table);
	}

	@Override
	public void increaseCapacity(int id) {
		Session session = entityManager.unwrap(Session.class);
		Tables table = session.get(Tables.class, id);
		table.setCapacity(table.getCapacity() + 1);
		session.saveOrUpdate(table);
	}

	@Override
	public void decreaseCapacity(int id) {
		Session session = entityManager.unwrap(Session.class);
		Tables table = session.get(Tables.class, id);
		if(table.getCapacity() > 0) {
			table.setCapacity(table.getCapacity() - 1);
		}
		session.saveOrUpdate(table);
	}

}
