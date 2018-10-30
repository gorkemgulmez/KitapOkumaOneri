package model;

import javafx.beans.property.SimpleStringProperty;

public class BookModel {

	private SimpleStringProperty isbn, name, author, date, publisher;
	private String imageLink;
	
	public BookModel(String isbn, String name, String author, String date, String publisher, String imageLink) {
		this.isbn = new SimpleStringProperty(isbn);
		this.name = new SimpleStringProperty(name);
		this.author = new SimpleStringProperty(author);
		this.date = new SimpleStringProperty(date);
		this.publisher = new SimpleStringProperty(publisher);
		this.imageLink = imageLink;
	}
	
	public String getIsbn() {
		return isbn.get();
	}
	
	public String getName() {
		return name.get();
	}
	
	public String getAuthor() {
		return author.get();
	}
	
	public String getDate() {
		return date.get();
	}
	
	public String getPublisher() {
		return publisher.get();
	}
	
	public String getImageLink() {
		return imageLink;
	}
	
	public void setIsbn(String isbn) {
		this.isbn.set(isbn);
	}
	
	public void setName(String name) {
		this.name.set(name);
	}
	
	public void setAuthor(String author) {
		this.author.set(author);
	}
	
	public void setDate(String publish_date) {
		this.date.set(publish_date);
	}
	
	public void setPublisher(String publisher) {
		this.publisher.set(publisher);
	}
	
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
}
