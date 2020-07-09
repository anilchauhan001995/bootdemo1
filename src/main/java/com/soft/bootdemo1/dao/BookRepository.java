package com.soft.bootdemo1.dao;


import com.soft.bootdemo1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
