package com.example.librarymanagement.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.librarymanagement.entities.Author;
import com.example.librarymanagement.repository.AuthorRepository;
import com.example.librarymanagement.services.AuthorService;
@Service
public class AuthorServiceImplementor implements AuthorService{

	@Autowired
	private AuthorRepository authorRepo;
	
	public Author addAuthor(Author author) {
		return authorRepo.save(author);
	}
	
	@Override
	public List<Author> getAuthors(){
		return authorRepo.findAll();
	}
	
	@Override
	public Author getAuthorById(Integer authorid) {
		return authorRepo.getById(authorid);
	}
	
	@Override
	public void deleteAuthor(Integer authorId) {
		if(getAuthorById(authorId)!=null) {
			authorRepo.deleteById(authorId);
		}
	}

}


