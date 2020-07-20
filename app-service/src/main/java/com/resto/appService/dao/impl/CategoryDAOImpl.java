package com.resto.appService.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.resto.appService.dao.CategoryDAO;
import com.resto.commonModel.entity.Category;
import com.resto.commonModel.entity.CategoryType;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<CategoryType> getAllCategoryTypes() {
		Session session = entityManager.unwrap(Session.class);
		Query<CategoryType> query = session.createQuery("from CategoryType order by typeName asc", CategoryType.class);
		return query.getResultList();
	}

	@Override
	public Category addCategory(Category category) {
		System.out.println(category.toString());
		Session session = entityManager.unwrap(Session.class);
		CategoryType categoryType = session.get(CategoryType.class, category.getCategoryTypeId());
		category.setCategoryType(categoryType);
		session.saveOrUpdate(category);
		return category;
	}

	@Override
	public List<Category> getAllCategory() {
		Session session = entityManager.unwrap(Session.class);
		Query<Category> query = session.createQuery("from Category order by categoryName asc", Category.class);
		return query.getResultList();
	}

	@Override
	public void deleteCategory(int id) {
		Session session = entityManager.unwrap(Session.class);
		session.delete(session.get(Category.class, id));
	}

}
