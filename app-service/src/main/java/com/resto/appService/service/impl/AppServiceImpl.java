package com.resto.appService.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resto.appService.dao.CategoryDAO;
import com.resto.appService.dao.ItemDAO;
import com.resto.appService.dao.PersonDAO;
import com.resto.appService.dao.TableDAO;
import com.resto.appService.service.AppService;
import com.resto.commonModel.entity.Category;
import com.resto.commonModel.entity.CategoryType;
import com.resto.commonModel.entity.Item;
import com.resto.commonModel.entity.Person;
import com.resto.commonModel.entity.Tables;

@Service
public class AppServiceImpl implements AppService {
	
	@Autowired
	private TableDAO tableDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private PersonDAO personDAO;

	@Override
	@Transactional
	public List<Tables> getAllTables() {
		return tableDAO.getAllTables();
	}

	@Override
	@Transactional
	public Tables getTable(int id) {
		return tableDAO.getTable(id);
	}

	@Override
	@Transactional
	public Tables addTable(Tables table) {
		return tableDAO.addTable(table);
	}

	@Override
	@Transactional
	public void deleteTable(int id) {
		tableDAO.deleteTable(id);
	}

	@Override
	@Transactional
	public void increaseCapacity(int id) {
		tableDAO.increaseCapacity(id);
	}

	@Override
	@Transactional
	public void decreaseCapacity(int id) {
		tableDAO.decreaseCapacity(id);
	}

	@Override
	@Transactional
	public List<CategoryType> getAllCategoryTypes() {
		return categoryDAO.getAllCategoryTypes();
	}

	@Override
	@Transactional
	public Category addCategory(Category category) {
		return categoryDAO.addCategory(category);
	}

	@Override
	@Transactional
	public List<Category> getAllCategories() {
		return categoryDAO.getAllCategory();
	}

	@Override
	@Transactional
	public Item saveItem(Item item) {
		return itemDAO.saveItem(item);
	}

	@Override
	@Transactional
	public List<Item> getItems(int categoryId) {
		return itemDAO.getItems(categoryId);
	}

	@Override
	@Transactional
	public Item getItem(int itemId) {
		return itemDAO.getItem(itemId);
	}

	@Override
	@Transactional
	public void deleteItem(int id) {
		itemDAO.deleteItem(id);
	}

	@Override
	@Transactional
	public void deleteCategory(int id) {
		categoryDAO.deleteCategory(id);
	}

	@Override
	@Transactional
	public List<Person> getPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Person savePerson(Person person) {
		return personDAO.savePerson(person);
	}

	@Override
	@Transactional
	public Person deactivatePerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

}
