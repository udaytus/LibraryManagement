package com.example.librarymanagement.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagement.entities.Author;
import com.example.librarymanagement.entities.Book;
import com.example.librarymanagement.entities.BookDTO;
import com.example.librarymanagement.repository.AuthorRepository;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.services.AuthorService;
import com.example.librarymanagement.services.BookService;
import com.example.librarymanagement.utils.Mapper;

@Service
public class BookServiceImplementor implements BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired 
	private Mapper mapper;
	
	@Override
	public BookDTO addBook(BookDTO book) {
		
		BookDTO BookToReturn =null;
		//check duplicate
		BookDTO bookInTable = getBookById(book.getBookCode());
		
		if(bookInTable==null) {
			//get author of that particular id
			Author author=authorService.getAuthorById(book.getAuthorId());
			
			//dto to entity
			Book bookToSave = mapper.DTOtoEntity(book, author);
			
			//save the entity
			bookRepo.save(bookToSave);
			
			//convert entity to dto
			BookToReturn =mapper.entityToDTO(bookToSave);
			
		}
		return BookToReturn;
	}
	
	@Override
	public BookDTO getBookById(Integer id) {
		BookDTO bookToReturn=null;
		
		try {
			Optional<Book> bookFound=bookRepo.findById(id);
			if(bookFound.isPresent()) {
				bookToReturn=mapper.entityToDTO(bookFound.get());
			}
		}
		catch(NoSuchElementException e) {
			System.out.println("No Book Found");
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		return bookToReturn;
	}
	
	@Override
	public List<BookDTO> getBooks(){
		List<Book> books= bookRepo.findAll();
		List<BookDTO> booksToReturn=books.stream().map(book -> mapper.entityToDTO(book))
				.collect(Collectors.toList());
		return booksToReturn;
	}
	
	@Override
	public BookDTO updateBook(BookDTO book, Integer id) {
		
		BookDTO bookInTable = getBookById(id);
		
		if(bookInTable !=null) {
			//update
			bookInTable.setBookName(book.getBookName());
			bookInTable.setAuthorId(book.getAuthorId());
			
			//specfic author
			Author author =authorService.getAuthorById(bookInTable.getAuthorId());
			
			//dto to entity
			Book bookEntity =mapper.DTOtoEntity(book, author);
			
			//save
			bookRepo.save(bookEntity);
			
			//entity to dto
			bookInTable = mapper.entityToDTO(bookEntity);
		}
		return bookInTable;
	}
	
	@Override
	public void deleteBook(Integer id) {
		if (getBookById(id) != null) {

			BookDTO bookInTable = getBookById(id);

			// get author of that particular id
			Author author = authorService.getAuthorById(bookInTable.getAuthorId());

			bookRepo.delete(mapper.DTOtoEntity(getBookById(id), author));
		}
	}
}
	
