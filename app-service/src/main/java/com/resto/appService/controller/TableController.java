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
	
	@GetMapping("/table/{tableId}")
	public Response<Tables> getTable(@PathVariable Long tableId){
		List<Tables> tables = new ArrayList<Tables>();
		tables.add(service.getTable(tableId));
		return new Response<Tables>("tables", tables);
	}
	
	@PostMapping("/table")
	public Response<Tables> addTables(@RequestBody Tables table){
		List<Tables> tables = new ArrayList<Tables>();
		tables.add(service.addTable(table));
		return new Response<Tables>("tables", tables);
	}
	
	@PostMapping("/table/{tableId}")
	public void deleteTable(@PathVariable Long tableId) {
		service.deleteTable(tableId);
	}
	
	@PostMapping("/table/increase/{tableId}")
	public void increaseCapacity(@PathVariable Long tableId) {
		service.increaseCapacity(tableId);
	}
	
	@PostMapping("/table/decrease/{tableId}")
	public void decreaseCapacity(@PathVariable Long tableId) {
		service.decreaseCapacity(tableId);
	}
}
