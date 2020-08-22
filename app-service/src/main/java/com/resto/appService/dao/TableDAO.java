package com.resto.appService.dao;

import java.util.List;

import com.resto.commonModel.entity.Tables;

public interface TableDAO {
	public List<Tables> getAllTables();
	public Tables addTable(Tables table);
	public void deleteTable(Long tableId);
	public void increaseCapacity(Long tableId);
	public void decreaseCapacity(Long tableId);
	public Tables getTable(Long tableId);
}
