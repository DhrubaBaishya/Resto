package com.resto.appService.service;

import java.util.List;

import com.resto.commonModel.entity.Category;
import com.resto.commonModel.entity.CategoryType;
import com.resto.commonModel.entity.Item;
import com.resto.commonModel.entity.Person;
import com.resto.commonModel.entity.Tables;
import com.resto.commonModel.entity.Tax;

public interface AppService {
	public List<Tables> getAllTables();
	public Tables addTable(Tables table);
	public void deleteTable(int id);
	public void increaseCapacity(int id);
	public void decreaseCapacity(int id);
	public List<CategoryType> getAllCategoryTypes();
	public Category addCategory(Category category);
	public List<Category> getAllCategories();
	public Tables getTable(int id);
	public Item saveItem(Item item);
	public List<Item> getItems(int categoryId);
	public Item getItem(int itemId);
	public void deleteItem(int id);
	public void deleteCategory(int id);
	public List<Person> getPersons(int page, int size, String status);
	public Person savePerson(Person person);
	public void deactivatePerson(Long personId);
	public Person updatePerson(Person person);
	public void activatePerson(Long personId);
	public Tax saveTax(Tax tax);
	public List<Tax> getAllTaxes();
	public void deleteTax(Long taxId);
}
