package com.example.librarymanagement.services;

import java.util.List;

import com.example.librarymanagement.entities.Author;

public interface AuthorService {
	//add author
	public Author addAuthor(Author author);
	//get all authors
	public List<Author> getAuthors();
	//get specific author
	public Author getAuthorById(Integer authorId);
	//delete a specific author
	public void deleteAuthor(Integer authorId);
}
