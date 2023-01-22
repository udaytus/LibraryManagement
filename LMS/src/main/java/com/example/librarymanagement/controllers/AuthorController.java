package com.example.librarymanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagement.entities.Author;
import com.example.librarymanagement.services.AuthorService;

@RestController
public class AuthorController {

		
		@Autowired
		private AuthorService authorService;

		
	

		@GetMapping("/author")
		public ResponseEntity<List<Author>> getAllauthors() {
			try {
				List<Author> authors = authorService.getAuthors();
				
				if (authors.isEmpty() || authors.size() == 0) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(authors, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		
		

		@GetMapping("/author/{id}")
		public ResponseEntity<Author> getAuthor(@PathVariable Integer id) {
			Author author = authorService.getAuthorById(id);
			
			if (author != null) {
				return new ResponseEntity<Author>(author, HttpStatus.OK);
			}

			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		}

		
		

		@PostMapping("/author")
		public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
			try {			 
				return new ResponseEntity<Author>(authorService.addAuthor(author), HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Author>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		
		

		@PutMapping("/author/{authorId}")
		public ResponseEntity<Author> updateAuthor(@RequestBody Author author, @PathVariable Integer authorId) {
			try {
				//get author record to be updated
				Author authorToUpdate = authorService.getAuthorById(authorId);
				
				//set the values
				authorToUpdate.setAuthorName(author.getAuthorName());
				
				//update the changes
				return new ResponseEntity<Author>(authorService.addAuthor(authorToUpdate), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Author>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		
		

		@DeleteMapping("/author/{id}")
		public ResponseEntity<Author> deleteAuthor(@PathVariable Integer id) {
			try {
				authorService.deleteAuthor(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
}

