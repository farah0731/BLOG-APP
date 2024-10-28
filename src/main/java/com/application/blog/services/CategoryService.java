package com.application.blog.services;

import java.util.List;

import com.application.blog.payloads.CategoryDto;

public interface CategoryService {

	//create
	
	 CategoryDto createCategory(CategoryDto category);
	
	
	//update
    CategoryDto updateCategory(CategoryDto category,Integer categoryId);

	//Delete
	 void deleteCategory(Integer categoryId);
	
	
	//get
	 CategoryDto getCategory(Integer categoryId);
	 
	 
	 
	 //getAll
	 List<CategoryDto> getCategories();
	
}
