package com.vijay.ebook.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vijay.ebook.dto.model.CategoryDto;

public interface CategoryService {

	ResponseEntity<CategoryDto> addCategory(CategoryDto categoryDto);

	ResponseEntity<List<CategoryDto>> getAllCategory();

	ResponseEntity<String> deleteCategory(Long categoryId);

	ResponseEntity<CategoryDto> getCategory(String categoryName);

}
