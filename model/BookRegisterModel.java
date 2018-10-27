package model;

public class BookRegisterModel {

	private String  isbn, name, author, publish_date, publisher, imageLink_s, imageLink_m, image_l;

	public BookRegisterModel(String isbn, String name, String author, String publish_date, String publisher,
			String imageLink_s, String imageLink_m, String image_l) {
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.publish_date = publish_date;
		this.publisher = publisher;
		this.imageLink_s = imageLink_s;
		this.imageLink_m = imageLink_m;
		this.image_l = image_l;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getImageLink_s() {
		return imageLink_s;
	}

	public void setImageLink_s(String imageLink_s) {
		this.imageLink_s = imageLink_s;
	}

	public String getImageLink_m() {
		return imageLink_m;
	}

	public void setImageLink_m(String imageLink_m) {
		this.imageLink_m = imageLink_m;
	}

	public String getImage_l() {
		return image_l;
	}

	public void setImage_l(String image_l) {
		this.image_l = image_l;
	}
	
}
