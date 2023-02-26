package com.vijay.ebook.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
//public class CategoryServiceImplTest {
//	
//	
//	
//}
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vijay.ebook.entity.Category;
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

}
