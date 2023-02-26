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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vijay.ebook.dto.model.CategoryDto;
import com.vijay.ebook.service.CategoryService;
import com.vijay.ebook.service.BookService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

}
