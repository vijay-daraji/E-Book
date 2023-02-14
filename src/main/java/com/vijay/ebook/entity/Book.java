package com.vijay.ebook.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="book")
public class Book {
	
	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String description;
	
	@ManyToOne
	private Category category;
	private String autherName;
	private Date releaseDate;
	
	public Book() {
		super();
	}

	public Book(long id, String title, String description, Category category, String autherName, Date releaseDate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.category = category;
		this.autherName = autherName;
		this.releaseDate = releaseDate;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getAutherName() {
		return autherName;
	}
	public void setAutherName(String autherName) {
		this.autherName = autherName;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
}
