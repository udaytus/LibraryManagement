package com.example.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.librarymanagement.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
