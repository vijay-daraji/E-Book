package com.vijay.ebook.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
//public class CategoryServiceImplTest {
//	
//	
//	
//}
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.vijay.ebook.dto.model.CategoryDto;
import com.vijay.ebook.entity.Category;
import com.vijay.ebook.repository.CategoryRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@BeforeEach
	void setUp() {
		this.categoryServiceImpl = new CategoryServiceImpl(this.categoryRepository, this.modelMapper);
	}
	
//	@BeforeEach
//	void setUp() {
//		this.categoryServiceImpl = new CategoryServiceImpl(this.categoryRepository);
//	}
	
	@Test
	void getAllCategory() {
		Category category1 = new Category(101L, "category1",null);
		Category category2 = new Category(102L, "category2",null);
		List<Category> list = new ArrayList<>();
		list.add(category1);
		list.add(category2);
		Mockito.when(categoryRepository.findAll()).thenReturn(list);
		categoryServiceImpl.getAllCategory();
		
		assertEquals(2, categoryServiceImpl.getAllCategory().getBody().size());
		
//		verify(categoryRepository).findAll();
	}
	
	@Test
	void getCategory() {
		Category category1 = new Category(101L, "category1",null);
		Mockito.when(categoryRepository.findByCategoryName("category1")).thenReturn(category1);
		
		CategoryDto categoryDto = categoryServiceImpl.getCategory("category1").getBody();
		assertEquals( "category1",categoryDto.getCategoryName());
	}
	
	@Test
	void getCategory__NotFound() {
		Mockito.when(categoryRepository.findByCategoryName("category1")).thenReturn(null);
		
		assertThrows(RuntimeException.class, ()-> categoryServiceImpl.getCategory("category1"));
	}

}
