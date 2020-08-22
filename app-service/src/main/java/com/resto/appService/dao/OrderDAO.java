package com.resto.appService.dao;

import java.util.List;

import com.resto.commonModel.entity.Order;
import com.resto.commonModel.queryUtil.SearchCriteria;

public interface OrderDAO {
	public List<Order> getAllOrders(int page, int size);
	public Order getOrder(Long tableId);
	public Order saveOrder(Order order);
	public List<Order> searchOrder(List<SearchCriteria> params, int page, int size);
}
