package com.example.librarymanagement.services;

import java.util.List;

import com.example.librarymanagement.entities.BookDTO;

public interface BookService {
	
//add new book
	public BookDTO addBook(BookDTO book);
	//get all books
	public List<BookDTO> getBooks();
	//get specific book
	public BookDTO getBookById(Integer id);
	//update a specific book
	public BookDTO updateBook(BookDTO book, Integer id);
	//delete a specific book
	public void deleteBook(Integer id);
}
