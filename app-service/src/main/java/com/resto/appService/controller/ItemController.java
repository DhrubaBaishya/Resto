package com.resto.appService.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resto.appService.service.AppService;
import com.resto.commonModel.entity.Item;
import com.resto.commonModel.response.Response;

@RestController
@RequestMapping("/api/v1")
public class ItemController {

	@Autowired
	private AppService service;
	
	@GetMapping("/item/{id}")
	public Response<Item> getItem(@PathVariable int id){
		List<Item> items = new ArrayList<Item>();
		items.add(service.getItem(id));
		return new Response<Item>("Item", items);
	}
	
	@GetMapping("/item/{categoryId}")
	public Response<Item> getItems(@PathVariable int categoryId){
		return new Response<Item>("Item", service.getItems(categoryId));
	}
	
	@PostMapping("/item")
	public Response<Item> saveItem(@RequestBody Item item){
		List<Item> items = new ArrayList<Item>();
		items.add(service.saveItem(item));
		return new Response<Item>("Item", items);
	}
	
	@PostMapping("/item/{id}")
	public void deleteItem(@PathVariable int id){
		service.deleteItem(id);
	}
	
}
