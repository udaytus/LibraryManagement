package com.example.librarymanagement.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "author_details")
public class Author implements Serializable{
	

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer authorId;

		@Column(name = "author_name")
		private String authorName;

		public Author() {
			super();
		}

		/**
		 * @param authorId
		 * @param authorName
		 */
		public Author(Integer authorId, String authorName) {
			super();
			this.authorId = authorId;
			this.authorName = authorName;
		}

		/**
		 * @return the authorId
		 */
		public Integer getAuthorId() {
			return authorId;
		}

		/**
		 * @param authorId the authorId to set
		 */
		public void setAuthorId(Integer authorId) {
			this.authorId = authorId;
		}

		/**
		 * @return the authorName
		 */
		public String getAuthorName() {
			return authorName;
		}

		/**
		 * @param authorName the authorName to set
		 */
		public void setAuthorName(String authorName) {
			this.authorName = authorName;
		}

		@Override
		public String toString() {
			return "Author [authorId=" + authorId + ", authorName=" + authorName + "]";
		}
}

