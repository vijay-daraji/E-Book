package com.vijay.ebook.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.vijay.ebook.entity.Category;
import com.vijay.ebook.repository.CategoryRepository;

//DataJpaTest is for in-memory data, roll back after test
@DataJpaTest
//if already any database is configured replace with in-memory
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	void findByCategoryNameTest() {
		Category category1 = new Category(101L, "category2",null);
		categoryRepository.save(category1);
		
		Category category = categoryRepository.findByCategoryName("category2");
		assertEquals("category2",category.getCategoryName());
	}
	
}
