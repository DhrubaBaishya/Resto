package com.resto.appService.dao;

import java.util.List;

import com.resto.commonModel.entity.Tables;

public interface TableDAO {
	public List<Tables> getAllTables();
	public Tables addTable(Tables table);
	public void deleteTable(int id);
	public void increaseCapacity(int id);
	public void decreaseCapacity(int id);
	public Tables getTable(int id);
}
