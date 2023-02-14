package com.vijay.ebook.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import com.vijay.ebook.dto.model.CategoryDto;
import com.vijay.ebook.entity.Category;

public class CategoryMapper {

	public static List<CategoryDto> toCategoryDtos(List<Category> listOfCategory){
		List<CategoryDto> categoryDtos = listOfCategory.stream().map(category -> 
				new ModelMapper().map(category, CategoryDto.class))
					.collect(Collectors.toList());
		return categoryDtos;
	}
}
