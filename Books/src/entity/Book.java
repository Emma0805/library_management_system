package entity;

import java.sql.Date;

public class Book {
	private int bookId;
	private long ISBN;
	private String author;
	private String name;
	private Integer studentId;
	private Date dateCheckout;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Date getDateCheckout() {
		return dateCheckout;
	}

	public void setDateCheckout(Date dateCheckout) {
		this.dateCheckout = dateCheckout;
	}

}
