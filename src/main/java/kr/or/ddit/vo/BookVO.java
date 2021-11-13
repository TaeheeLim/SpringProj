package kr.or.ddit.vo;

import lombok.Getter;

//자바빈 클래스
public class BookVO {
	private String bookId;
	private String title;
	private String category;
	private String price;
	private String insertDate;
	
	public BookVO() {}
	
	
	public BookVO(String bookId, String title, String category, String price, String insertDate) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.category = category;
		this.price = price;
		this.insertDate = insertDate;
	}


	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	@Override
	public String toString() {
		return "BookVO [bookId=" + bookId + ", title=" + title + ", category=" + category + ", price=" + price
				+ ", insertDate=" + insertDate + "]";
	}
	
	
}
