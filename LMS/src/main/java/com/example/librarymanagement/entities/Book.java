package com.example.librarymanagement.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_details")
public class Book {
	
	

		@Id
		private Integer bookCode;

		@Column(name = "book_name")
		private String bookName;

		@CreationTimestamp
		@Column(name = "added_on", nullable = false, updatable = false)
		private Date addedOn;

		@OneToOne
		@JoinColumn(name = "author_of_book")
		private Author author;

		public Book() {
			super();
		}

		
		public Book(Integer bookCode, String bookName, Date addedOn, Author author) {
			super();
			this.bookCode = bookCode;
			this.bookName = bookName;
			this.addedOn = addedOn;
			this.author = author;
		}

		public Integer getBookCode() {
			return bookCode;
		}

		public void setBookCode(Integer bookCode) {
			this.bookCode = bookCode;
		}

		public String getBookName() {
			return bookName;
		}

		public void setBookName(String bookName) {
			this.bookName = bookName;
		}

		public Date getAddedOn() {
			return addedOn;
		}

		public void setAddedOn(Date addedOn) {
			this.addedOn = addedOn;
		}

		public Author getAuthor() {
			return author;
		}

		public void setAuthor(Author author) {
			this.author = author;
		}

		@Override
		public String toString() {
			return "Book [bookCode=" + bookCode + ", bookName=" + bookName + ", addedOn=" + addedOn + ", author="
					+ author.getAuthorName() + "]";
		}
}

