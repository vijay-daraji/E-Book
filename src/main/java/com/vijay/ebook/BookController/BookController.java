package com.vijay.ebook.BookController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.ebook.dto.model.BookDto;
import com.vijay.ebook.service.BookService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping("/books")
	private ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto) {
		return bookService.addBook(bookDto);
	}
	
	@GetMapping("/books")
	private ResponseEntity<List<BookDto>> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@PutMapping("/books/{bookId}")
	private ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable Long bookId) {
		return bookService.updateBook(bookDto, bookId);
	} 
	
	@DeleteMapping("/books/{bookId}")
	private ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
		return bookService.deleteBook(bookId);
	} 
}
