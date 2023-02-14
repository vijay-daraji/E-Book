package com.vijay.ebook.BookController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.ebook.dto.model.CategoryDto;
import com.vijay.ebook.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/categories")
	private ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDto){
		return categoryService.addCategory(categoryDto);
	}
	
	@GetMapping("/categories")
	private ResponseEntity<List<CategoryDto>> getAllCategory(){
		return categoryService.getAllCategory();
	}
	
	@DeleteMapping("/categories/{categoryId}")
	private ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
		return categoryService.deleteCategory(categoryId);
	}
	
	@GetMapping("/categories/{categoryName}")
	private ResponseEntity<CategoryDto> getCategory(@PathVariable String categoryName){
		return categoryService.getCategory(categoryName);
	}

}
