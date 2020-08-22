package com.resto.appService.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resto.appService.dao.AreaDAO;
import com.resto.appService.dao.CategoryDAO;
import com.resto.appService.dao.ItemDAO;
import com.resto.appService.dao.OrderDAO;
import com.resto.appService.dao.PersonDAO;
import com.resto.appService.dao.TableDAO;
import com.resto.appService.dao.TaxDAO;
import com.resto.appService.service.AppService;
import com.resto.commonModel.entity.Area;
import com.resto.commonModel.entity.Category;
import com.resto.commonModel.entity.CategoryType;
import com.resto.commonModel.entity.Item;
import com.resto.commonModel.entity.Order;
import com.resto.commonModel.entity.Person;
import com.resto.commonModel.entity.Tables;
import com.resto.commonModel.entity.Tax;
import com.resto.commonModel.queryUtil.SearchCriteria;

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
	
	@Autowired
	private TaxDAO taxDAO;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private AreaDAO areaDAO;

	@Override
	@Transactional
	public List<Tables> getAllTables() {
		return tableDAO.getAllTables();
	}

	@Override
	@Transactional
	public Tables getTable(Long tableId) {
		return tableDAO.getTable(tableId);
	}

	@Override
	@Transactional
	public Tables addTable(Tables table) {
		return tableDAO.addTable(table);
	}

	@Override
	@Transactional
	public void deleteTable(Long tableId) {
		tableDAO.deleteTable(tableId);
	}

	@Override
	@Transactional
	public void increaseCapacity(Long tableId) {
		tableDAO.increaseCapacity(tableId);
	}

	@Override
	@Transactional
	public void decreaseCapacity(Long tableId) {
		tableDAO.decreaseCapacity(tableId);
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
	public List<Person> getPersons(int page,int size, String status) {
		return personDAO.getPersons(page,size,status);
	}

	@Override
	@Transactional
	public Person savePerson(Person person) {
		return personDAO.savePerson(person);
	}

	@Override
	@Transactional
	public void deactivatePerson(Long personId) {
		personDAO.deactivatePerson(personId);
	}

	@Override
	@Transactional
	public Person updatePerson(Person person) {
		return personDAO.updatePerson(person);
	}

	@Override
	@Transactional
	public void activatePerson(Long personId) {
		personDAO.activatePerson(personId);
	}

	@Override
	@Transactional
	public Tax saveTax(Tax tax) {
		return taxDAO.saveTax(tax);
	}

	@Override
	@Transactional
	public List<Tax> getAllTaxes() {
		return taxDAO.getAllTaxes();
	}

	@Override
	@Transactional
	public void deleteTax(Long taxId) {
		taxDAO.deleteTax(taxId);
	}

	@Override
	@Transactional
	public List<Order> getAllOrders(int page, int size) {
		return orderDAO.getAllOrders(page,size);
	}

	@Override
	@Transactional
	public Order getOrder(Long tableId) {
		return orderDAO.getOrder(tableId);
	}

	@Override
	@Transactional
	public Order saveOrder(Order order) {
		return orderDAO.saveOrder(order);
	}

	@Override
	@Transactional
	public List<Order> searchOrder(List<SearchCriteria> params, int page, int size) {
		return orderDAO.searchOrder(params, page, size);
	}

	@Override
	@Transactional
	public List<Area> getAllArea() {
		return areaDAO.getAllArea();
	}

	@Override
	@Transactional
	public Area saveArea(Area area) {
		return areaDAO.saveArea(area);
	}

	@Override
	@Transactional
	public void deleteArea(Long areaId) {
		areaDAO.deleteArea(areaId);
	}

}
