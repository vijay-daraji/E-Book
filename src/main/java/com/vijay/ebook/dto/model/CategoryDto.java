package com.vijay.ebook.dto.model;

import jakarta.validation.constraints.NotNull;

public class CategoryDto {
	
	private Long id;
	@NotNull
	private String categoryName;
	
	public CategoryDto(Long id, @NotNull String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}
	public CategoryDto() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

}
