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
import com.resto.commonModel.entity.Category;
import com.resto.commonModel.entity.CategoryType;
import com.resto.commonModel.response.Response;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	
	@Autowired
	private AppService service;

	@GetMapping("/category/type")
	public Response<CategoryType> getAllCategoryTypes(){
		return new Response<CategoryType>("Category Types",service.getAllCategoryTypes());
	}
	
	@GetMapping("/category")
	public Response<Category> getAllCategories(){
		return new Response<Category>("Category",service.getAllCategories());
	}
	
	@PostMapping("/category")
	public Response<Category> addCategory(@RequestBody Category category){
		List<Category> categories = new ArrayList<Category>();
		categories.add(service.addCategory(category));
		return new Response<Category>("Category", categories);
	}
	
	@PostMapping("/category/{id}")
	public void deleteCategory(@PathVariable int id) {
		service.deleteCategory(id);
	}
	
}
