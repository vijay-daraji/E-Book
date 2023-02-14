package com.vijay.ebook.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="category")
public class Category {

	@Id
	@GeneratedValue
	private Long id;
	private String categoryName;
	
	@OneToMany(mappedBy="category")
	private List<Book> books;
	
	public Category() {
		super();
	}
	
	public Category(Long id, String categoryName, List<Book> books) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.books = books;
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
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	
}
