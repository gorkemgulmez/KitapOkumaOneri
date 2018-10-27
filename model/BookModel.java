package model;

import javafx.beans.property.SimpleStringProperty;

public class BookModel {

	private SimpleStringProperty isbn, name, author, publish_date, publisher;
	private String imageLink;
	
	public BookModel(String isbn, String name, String author, String publish_date, String publisher, String imageLink) {
		this.isbn = new SimpleStringProperty(isbn);
		this.name = new SimpleStringProperty(name);
		this.author = new SimpleStringProperty(author);
		this.publish_date = new SimpleStringProperty(publish_date);
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
	
	public String getPublish_Date() {
		return publish_date.get();
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
	
	public void setPublish_Date(String publish_date) {
		this.publish_date.set(publish_date);
	}
	
	public void setPublisher(String publisher) {
		this.publisher.set(publisher);
	}
	
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
}
