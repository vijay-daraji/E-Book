package com.vijay.ebook.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.vijay.ebook.dto.model.BookDto;
import com.vijay.ebook.entity.Book;

@Component
public class BookMapper {
	
	public static List<BookDto> toBookDtos(List<Book> books){
		List<BookDto> bookDtos = books.stream().map(book -> 
				new ModelMapper().map(book, BookDto.class))
					.collect(Collectors.toList());
		return bookDtos;
	}

}
