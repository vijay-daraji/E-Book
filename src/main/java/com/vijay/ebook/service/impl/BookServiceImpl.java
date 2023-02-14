package com.vijay.ebook.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vijay.ebook.entity.Book;
import com.vijay.ebook.entity.Category;
import com.vijay.ebook.dto.mapper.BookMapper;
import com.vijay.ebook.dto.model.BookDto;
import com.vijay.ebook.repository.BookRepository;
import com.vijay.ebook.repository.CategoryRepository;
import com.vijay.ebook.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<BookDto> addBook(BookDto bookDto) {
		Book bookEntity = modelMapper.map(bookDto, Book.class);
		Category category = categoryRepository.findByCategoryName(bookDto.getCategoryName());
		bookEntity.setCategory(category);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(bookDto.getReleaseDate());
			bookEntity.setReleaseDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Book book = bookRepository.save(bookEntity);
		return new ResponseEntity<>(modelMapper.map(book, BookDto.class), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<BookDto>> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		return new ResponseEntity<>(BookMapper.toBookDtos(books), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BookDto> updateBook(BookDto bookDto, Long bookId) {
		Book bookEntity = modelMapper.map(bookDto, Book.class);
		bookEntity.setId(bookId);
		bookEntity.setCategory(categoryRepository.findByCategoryName(bookDto.getCategoryName()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(bookDto.getReleaseDate());
			bookEntity.setReleaseDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Book book = bookRepository.save(bookEntity);
		return new ResponseEntity<>(modelMapper.map(book, BookDto.class), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteBook(Long bookId) {
		bookRepository.deleteById(bookId);
		return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
	}

}
