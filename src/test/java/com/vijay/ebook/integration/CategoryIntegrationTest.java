package com.vijay.ebook.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import com.vijay.ebook.dto.model.CategoryDto;
import com.vijay.ebook.entity.Category;
import com.vijay.ebook.repository.CategoryRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryIntegrationTest {

	@LocalServerPort
	private int port;
	
	private String baseUrl = "http://localhost";
	
	private static RestTemplate restTemplate;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}
	
	@BeforeEach
	public void beforeSetup() {
		baseUrl = baseUrl + ":" + port + "/categories";
	}
	
	@AfterEach
	public void afterSetup() {
//		categoryRepository.deleteAll();
	}
	
	
	//update test is same as create
	@Test
	void createCategory() {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setCategoryName("category1");
		
		CategoryDto category = restTemplate.postForObject(baseUrl, categoryDto, CategoryDto.class);
		
		
		assertNotNull(category);
		assertThat(category.getId()).isNotNull();
		
		//delete after test
		categoryRepository.deleteById(category.getId());
	}
	
	@Test
	void getAllCategory() {
		List<CategoryDto> list = restTemplate.getForObject(baseUrl, List.class);
		
		assertNotNull(list);
		
	}
	
	@Test
	void getCategory() {
		CategoryDto categoryDto = restTemplate.getForObject(baseUrl+"/"+"cat1", CategoryDto.class);
		
		assertNotNull(categoryDto);
		assertEquals("cat1",categoryDto.getCategoryName());
		
	}
	
	@Test
	void deleteCategory() {
		//create before delete
		Category category = new Category();
		category.setCategoryName("delete");
		categoryRepository.save(category);
		
		restTemplate.delete(baseUrl+"/"+category.getId());
		
		Category category2 = categoryRepository.findByCategoryName("delete");
		assertNull(category2);
		
	}
	
	
}
