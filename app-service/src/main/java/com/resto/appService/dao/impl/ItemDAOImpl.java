package com.resto.appService.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resto.appService.dao.ItemDAO;
import com.resto.commonModel.entity.Category;
import com.resto.commonModel.entity.Item;

@Repository
public class ItemDAOImpl implements ItemDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Item saveItem(Item item) {
		Session session = entityManager.unwrap(Session.class);
		Category category = session.get(Category.class, item.getCategoryId());
		item.setCategory(category);
		session.saveOrUpdate(item);
		return item;
	}

	@Override
	public List<Item> getItems(int categoryId) {
		Session session = entityManager.unwrap(Session.class);
		Query<Item> query = session.createQuery("from Item where category = :pCategoryId",Item.class);
		query.setParameter("pCategoryId", categoryId);
		return query.getResultList();
	}

	@Override
	public Item getItem(int itemId) {
		Session session = entityManager.unwrap(Session.class);
		return session.get(Item.class, itemId);
	}

	@Override
	public void deleteItem(int id) {
		Session session = entityManager.unwrap(Session.class);
		Item item = session.get(Item.class, id);
		session.delete(item);
	}

}
