package com.resto.appService.dao;

import java.util.List;

import com.resto.commonModel.entity.Item;

public interface ItemDAO {
	public Item saveItem(Item item);
	public List<Item> getItems(int categoryId);
	public Item getItem(int itemId);
	public void deleteItem(int id);
}
