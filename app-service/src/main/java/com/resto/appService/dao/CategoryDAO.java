package com.resto.appService.dao;

import java.util.List;

import com.resto.commonModel.entity.Category;
import com.resto.commonModel.entity.CategoryType;

public interface CategoryDAO {
	public List<CategoryType> getAllCategoryTypes();
	public Category addCategory(Category category);
	public List<Category> getAllCategory();
	public void deleteCategory(int id);
}
