package com.example.librarymanagement.utils;

import org.springframework.stereotype.Service;

import com.example.librarymanagement.entities.Author;
import com.example.librarymanagement.entities.Book;
import com.example.librarymanagement.entities.BookDTO;

// Mapper class for mapping of dto and entity
@Service
public class Mapper {
	// method for entity to dto
	public BookDTO entityToDTO(Book book) {
		BookDTO bookdto= new BookDTO();
		bookdto.setAuthorId(book.getAuthor().getAuthorId());
		bookdto.setBookCode(book.getBookCode());
		bookdto.setBookName(bookdto.getBookName());
		bookdto.setAddedOn(bookdto.getAddedOn());
		return bookdto;
	}
	
	// map method for dto to entity
	public Book DTOtoEntity(BookDTO bookdto, Author author) {
		Book book = new Book();
		book.setAuthor(author);
		book.setBookCode(book.getBookCode());
		book.setBookName(book.getBookName());
		book.setAddedOn(book.getAddedOn());
		return book;
	}

}
