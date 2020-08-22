package com.resto.appService.service;

import java.util.List;

import com.resto.commonModel.entity.Area;
import com.resto.commonModel.entity.Category;
import com.resto.commonModel.entity.CategoryType;
import com.resto.commonModel.entity.Item;
import com.resto.commonModel.entity.Order;
import com.resto.commonModel.entity.Person;
import com.resto.commonModel.entity.Tables;
import com.resto.commonModel.entity.Tax;
import com.resto.commonModel.queryUtil.SearchCriteria;

public interface AppService {
	public List<Tables> getAllTables();
	public Tables addTable(Tables table);
	public void deleteTable(Long tableId);
	public void increaseCapacity(Long tableId);
	public void decreaseCapacity(Long tableId);
	public List<CategoryType> getAllCategoryTypes();
	public Category addCategory(Category category);
	public List<Category> getAllCategories();
	public Tables getTable(Long tableId);
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
	public List<Order> getAllOrders(int page, int size);
	public Order getOrder(Long tableId);
	public Order saveOrder(Order order);
	public List<Order> searchOrder(List<SearchCriteria> params, int page, int size);
	public List<Area> getAllArea();
	public Area saveArea(Area area);
	public void deleteArea(Long areaId);
}
