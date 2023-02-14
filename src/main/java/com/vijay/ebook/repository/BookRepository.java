package com.vijay.ebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vijay.ebook.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
