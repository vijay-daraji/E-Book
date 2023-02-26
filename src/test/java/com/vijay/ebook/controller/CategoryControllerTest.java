package com.vijay.ebook.controller;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vijay.ebook.dto.model.CategoryDto;
import com.vijay.ebook.service.CategoryService;
import com.vijay.ebook.service.BookService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.CoreMatchers.is;

@WebMvcTest
public class CategoryControllerTest {
	
	@MockBean
	private CategoryService CategoryService; 
	
	@MockBean
	private BookService BookService; 
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void createCategoryTest() throws Exception {
		CategoryDto categoryDto = new CategoryDto(101L, "category1");
		
		Mockito.when(CategoryService.addCategory(any(CategoryDto.class))).thenReturn(new ResponseEntity<>(categoryDto, HttpStatus.CREATED));
		
		this.mockMvc.perform(post("/categories")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(categoryDto)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.categoryName", is(categoryDto.getCategoryName())));
		
	}
	
	@Test
	void getAllCategory() throws Exception {
		CategoryDto categoryDto1 = new CategoryDto(101L, "category1");
		CategoryDto categoryDto2 = new CategoryDto(102L, "category2");
		List<CategoryDto> categoryDtos = new ArrayList<>();
		categoryDtos.add(categoryDto1);
		categoryDtos.add(categoryDto2);
		
		Mockito.when(CategoryService.getAllCategory()).thenReturn(new ResponseEntity<>(categoryDtos, HttpStatus.OK));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/categories"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.size()", is(categoryDtos.size())));
	}
	
	@Test
	void getCategory() throws Exception {
		CategoryDto categoryDto = new CategoryDto(101L, "category1");
		
		Mockito.when(CategoryService.getCategory("category1")).thenReturn(new ResponseEntity<>(categoryDto, HttpStatus.OK));
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/categories/{categoryName}", "category1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.categoryName", is(categoryDto.getCategoryName())));
	}

}
