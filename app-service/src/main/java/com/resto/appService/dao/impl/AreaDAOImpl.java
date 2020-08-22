package com.resto.appService.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resto.appService.dao.AreaDAO;
import com.resto.commonModel.entity.Area;

@Repository
public class AreaDAOImpl implements AreaDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Area> getAllArea() {
		Session session = entityManager.unwrap(Session.class);
		Query<Area> query = session.createQuery("from Area",Area.class);
		return query.getResultList();
	}

	@Override
	public Area saveArea(Area area) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(area);
		return area;
	}

	@Override
	public void deleteArea(Long areaId) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(session.get(Area.class, areaId));
	}

}
