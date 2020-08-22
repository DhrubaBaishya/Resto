package com.resto.appService.dao;

import java.util.List;

import com.resto.commonModel.entity.Area;

public interface AreaDAO {
	public List<Area> getAllArea();
	public Area saveArea(Area area);
	public void deleteArea(Long areaId);
}
