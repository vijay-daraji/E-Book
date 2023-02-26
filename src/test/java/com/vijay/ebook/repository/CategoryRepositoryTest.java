package com.vijay.ebook.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vijay.ebook.entity.Category;

@SpringBootTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Test
	void isCategoryExist() {
		
		Category category = new Category();
		category.setCategoryName("cat2");
		categoryRepository.save(category);
		
		Category categoryEntity = categoryRepository.findByCategoryName("cat1");
		
		assertThat(categoryEntity.getCategoryName()).isEqualTo("cat2");
	}
	
	

}
