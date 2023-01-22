package com.example.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.librarymanagement.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

}
