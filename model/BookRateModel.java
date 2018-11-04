package model;

public class BookRateModel {

	private String isbn;
	private int rate;
	
	public BookRateModel(String isbn, int rate) {
		this.isbn = isbn;
		this.rate = rate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
}
