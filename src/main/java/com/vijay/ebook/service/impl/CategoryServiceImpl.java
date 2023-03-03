package com.vijay.ebook.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

//	public CategoryServiceImpl(CategoryRepository categoryRepository) {
//		super();
//		this.categoryRepository = categoryRepository;
//	}

	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<CategoryDto> addCategory(CategoryDto categoryDto) {
		Category categoryEntity = modelMapper.map(categoryDto, Category.class);
		Category category = categoryRepository.save(categoryEntity);
		return new ResponseEntity<>(modelMapper.map(category, CategoryDto.class), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("id").descending());
		Page<Category> pageResult = categoryRepository.findAll(pageRequest);
		List<Category> listOfCategory;
		if(pageResult.hasContent()) {
			listOfCategory = pageResult.getContent();
		}else {
			listOfCategory = new ArrayList<Category>();
		}
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
		if(category==null) {
			throw new RuntimeException("category not found");
		}
		return new ResponseEntity<>(modelMapper.map(category, CategoryDto.class), HttpStatus.OK);
	}

}
