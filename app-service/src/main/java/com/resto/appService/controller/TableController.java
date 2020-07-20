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
import com.resto.commonModel.entity.Tables;
import com.resto.commonModel.response.Response;

@RestController
@RequestMapping("/api/v1")
public class TableController {
	
	@Autowired
	private AppService service;
	
	@GetMapping("/table")
	public Response<Tables> getAllTables(){
		List<Tables> tables = service.getAllTables();
		return new Response<Tables>("tables", tables);
	}
	
	@GetMapping("/table/{id}")
	public Response<Tables> getTable(@PathVariable int id){
		List<Tables> tables = new ArrayList<Tables>();
		tables.add(service.getTable(id));
		return new Response<Tables>("tables", tables);
	}
	
	@PostMapping("/table")
	public Response<Tables> addTables(@RequestBody Tables table){
		List<Tables> tables = new ArrayList<Tables>();
		tables.add(service.addTable(table));
		return new Response<Tables>("tables", tables);
	}
	
	@PostMapping("/table/{id}")
	public void deleteTable(@PathVariable int id) {
		service.deleteTable(id);
	}
	
	@PostMapping("/table/increase/{id}")
	public void increaseCapacity(@PathVariable int id) {
		service.increaseCapacity(id);
	}
	
	@PostMapping("/table/decrease/{id}")
	public void decreaseCapacity(@PathVariable int id) {
		service.decreaseCapacity(id);
	}
}
