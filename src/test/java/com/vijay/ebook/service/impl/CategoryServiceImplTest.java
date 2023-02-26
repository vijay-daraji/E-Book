package com.vijay.ebook.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vijay.ebook.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {
	
	@Mock
	private CategoryRepository categoryRepository;
	
	private CategoryServiceImpl categoryServiceImpl;
	
	@BeforeEach
	void setUp() {
		this.categoryServiceImpl = new CategoryServiceImpl(this.categoryRepository);
	}
	
	@Test
	void getAllCategory() {
		
		categoryServiceImpl.getAllCategory();
		
		verify(categoryRepository).findAll();
	}

}
