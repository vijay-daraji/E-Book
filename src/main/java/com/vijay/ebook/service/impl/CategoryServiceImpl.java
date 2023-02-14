package com.vijay.ebook.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vijay.ebook.dto.mapper.CategoryMapper;
import com.vijay.ebook.dto.model.CategoryDto;
import com.vijay.ebook.entity.Category;
import com.vijay.ebook.repository.CategoryRepository;
import com.vijay.ebook.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<CategoryDto> addCategory(CategoryDto categoryDto) {
		Category categoryEntity = modelMapper.map(categoryDto, Category.class);
		Category category = categoryRepository.save(categoryEntity);
		return new ResponseEntity<>(modelMapper.map(category, CategoryDto.class), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<Category> listOfCategory = categoryRepository.findAll();
		return new ResponseEntity<>(CategoryMapper.toCategoryDtos(listOfCategory), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
		return new ResponseEntity<>("category deleted successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoryDto> getCategory(String categoryName) {
		Category category = categoryRepository.findByCategoryName(categoryName);
		return new ResponseEntity<>(modelMapper.map(category, CategoryDto.class), HttpStatus.OK);
	}

}
