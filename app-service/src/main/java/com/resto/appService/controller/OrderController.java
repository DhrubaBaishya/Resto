package com.resto.appService.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resto.appService.service.AppService;
import com.resto.commonModel.entity.Order;
import com.resto.commonModel.queryUtil.SearchCriteria;
import com.resto.commonModel.response.Response;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	
	@Autowired
	private AppService service;
	
	@GetMapping(value = "/order",params = {"page","size"})
	public Response<Order> getAllOrders(@RequestParam int page, @RequestParam int size){
		return new Response<Order>("Order", service.getAllOrders(page,size));
	}
	
	@GetMapping(value = "/order",params = {"search","page","size"})
	public Response<Order> searchOrder(@RequestParam String search, @RequestParam int page, @RequestParam int size){
		List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(=|:|<|>)(\\S+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
            	SearchCriteria criteria = new SearchCriteria(matcher.group(1), 
                        matcher.group(2), matcher.group(3));
                params.add(criteria);
                System.out.println(criteria);
            }
        }
		return new Response<Order>("Order", service.searchOrder(params, page, size));
	}
	
	@GetMapping("/order/table/{tableId}")
	public Response<Order> getOrder(@PathVariable Long tableId){
		List<Order> orders = new ArrayList<Order>();
		Order order = service.getOrder(tableId);
		if(order != null)
			orders.add(service.getOrder(tableId));
		return new Response<Order>("Order", orders);
	}
	
	@PostMapping("/order")
	public Response<Order> saveOrder(@RequestBody Order order){
		service.saveOrder(order);
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		return new Response<Order>("Order", orders);
	}
	
	@PostMapping("/order/complete")
	public Response<Order> completeOrder(@RequestBody Order order){
		order.setStatus("COMPLETE");
		service.saveOrder(order);
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		return new Response<Order>("Order", orders);
	}
	
	@PostMapping("/order/cancel")
	public Response<Order> cancelOrder(@RequestBody Order order){
		order.setStatus("CANCELLED");
		service.saveOrder(order);
		List<Order> orders = new ArrayList<Order>();
		orders.add(order);
		return new Response<Order>("Order", orders);
	}

}
