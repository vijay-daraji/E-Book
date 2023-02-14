package com.vijay.ebook.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vijay.ebook.dto.model.BookDto;

import jakarta.validation.Valid;

public interface BookService {

	ResponseEntity<BookDto> addBook(@Valid BookDto bookDto);

	ResponseEntity<List<BookDto>> getAllBooks();

	ResponseEntity<BookDto> updateBook(BookDto bookDto, Long bookId);

	ResponseEntity<String> deleteBook(Long bookId);

}
