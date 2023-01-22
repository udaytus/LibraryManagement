package com.example.librarymanagement.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.example.librarymanagement.entities.Book;
import com.example.librarymanagement.entities.BookDTO;
import com.example.librarymanagement.services.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService bookservice;
	
	@GetMapping("/books")
	public ResponseEntity<List<BookDTO>> getAllBooks(){
		try {
			List<BookDTO> books=bookservice.getBooks();
			
			if(books.isEmpty() || books.size()==0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(books, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<BookDTO> getBook(@PathVariable Integer id){
		BookDTO book=bookservice.getBookById(id);
		
		if(book !=null) {
			return new ResponseEntity<BookDTO>(book , HttpStatus.OK);
		}
		return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/book")
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO book){ //deserialize object
		try {
			BookDTO bookSaved =bookservice.addBook(book);
			
			if(bookSaved !=null) {
				return new ResponseEntity<BookDTO>(bookSaved,HttpStatus.CREATED);
			}
			return new ResponseEntity<BookDTO>(HttpStatus.CONFLICT);
		}catch(HttpClientErrorException e) {
			e.printStackTrace();
			return new ResponseEntity<BookDTO>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("book/{id}")
	public BookDTO updateBook(@RequestBody BookDTO bookDTO, @PathVariable Integer id) {
		BookDTO bookUpdated=null;
		try {
			bookUpdated =bookservice.updateBook(bookDTO, id);
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		return bookUpdated;
	}
	
	@DeleteMapping("book/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable Integer id) {
		try {
			bookservice.deleteBook(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

}
