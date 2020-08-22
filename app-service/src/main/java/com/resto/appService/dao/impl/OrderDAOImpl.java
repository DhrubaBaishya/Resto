package com.resto.appService.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.resto.appService.dao.OrderDAO;
import com.resto.commonModel.entity.Order;
import com.resto.commonModel.entity.OrderItem;
import com.resto.commonModel.entity.OrderTax;
import com.resto.commonModel.entity.Tax;
import com.resto.commonModel.queryUtil.SearchCriteria;
import com.resto.commonModel.queryUtil.SearchQueryCriteriaConsumer;

@Repository
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Order> getAllOrders(int page, int size){
		Session session = entityManager.unwrap(Session.class);
		Query<Order> query = session.createQuery("from Order order by creationDate desc", Order.class);
		query.setFirstResult((page - 1) * size);
		query.setMaxResults(size);
		return query.getResultList();
	}
	
	@Override
	public Order getOrder(Long tableId) {
		Session session = entityManager.unwrap(Session.class);
		Query<Order> query = session.createQuery("from Order where tableId=:pTableId and status<>'COMPLETE'",Order.class);
		query.setParameter("pTableId", tableId);
		try {
			return query.getSingleResult();
		}
		catch(NoResultException e) {
			return null;
		}
	}

	@Override
	public Order saveOrder(Order order) {
		Session session = entityManager.unwrap(Session.class);
		if(order.getOrderId() == null) {
			Order existingOrder = getOrder(order.getTableId());
			if(existingOrder != null) {
				return existingOrder;
			}
			else {
				Query<Tax> query = session.createQuery("from Tax order by creationDate",Tax.class);
				List<Tax> taxes = query.getResultList();
				List<OrderTax> orderTaxes = new ArrayList<OrderTax>();
				for(Tax tax: taxes) {
					OrderTax orderTax = new OrderTax();
					orderTax.setTaxName(tax.getTaxName());
					orderTax.setPercentage(tax.getPercentage());
					orderTax.setIncluded("Y");
					orderTax.setMandatory(tax.getMandatory());
					orderTax.setOrder(order);
					orderTaxes.add(orderTax);
				}
				order.setTaxes(orderTaxes);
			}
		}
		Set<Long> orderIds = order.getItems().stream()
				.map(OrderItem::getItemId)
				.collect(Collectors.toSet());
		session.saveOrUpdate(order);
		removeItems(order,orderIds);
		return order;
	}
	
	private void removeItems(Order order, Set<Long> orderIds) {
		Session session = entityManager.unwrap(Session.class);
		Query<OrderItem> query = session.createQuery("from OrderItem where order.orderId = :pOrderId ",OrderItem.class);
		query.setParameter("pOrderId", order.getOrderId());
		List<OrderItem> items = query.getResultList();
		List<OrderItem> itemsToBeRemoved= items.stream()
				.filter(item -> !orderIds.contains(item.getItemId()))
				.collect(Collectors.toList());
		for(OrderItem item : itemsToBeRemoved) {
			session.remove(item);
		}
	}

	@Override
	public List<Order> searchOrder(List<SearchCriteria> params, int page, int size) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Order> query = builder.createQuery(Order.class);
		Root<Order> root = query.from(Order.class);
		Predicate predicate = builder.conjunction();
		SearchQueryCriteriaConsumer<Order> consumer = new SearchQueryCriteriaConsumer<Order>(predicate,builder,root);
		params.stream().forEach(consumer);
		predicate = consumer.getPredicate();
		query.where(predicate);
		query.orderBy(builder.desc(root.get("creationDate")));
		TypedQuery<Order> tQuery = entityManager.createQuery(query);
		tQuery.setFirstResult((page - 1) * size);
		tQuery.setMaxResults(size);
		return tQuery.getResultList();
	}

}
