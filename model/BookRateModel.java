package model;

public class BookRateModel {

	private int id, rate;
	
	public BookRateModel(int id, int rate) {
		this.id = id;
		this.rate = rate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
}
