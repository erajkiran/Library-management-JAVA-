package com.libraryMngt.model;

public class Book {
	private String id;
	private String name;
	private String author;
	private String publication;
	private Boolean isIssued;
	private String issueDate;
	private String returnDate;
	private String issuerId;

	public Book() {
		this.id = null;
		this.name = null;
		this.author= null;
		this.publication = null;
		this.isIssued=null;
		this.issueDate=null;
		this.returnDate=null;
		this.issuerId=null;
	}

	
	public Book(String id,String name, String author, String publication, Boolean isIssued,String issueDate,String returnDate,String issuerId) {
		this.id = id;
		this.name=name;
		this.author=author;
		this.publication=publication;
		this.isIssued=isIssued;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.issuerId = issuerId;
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	public String getPublication() {
		return publication;
	}


	public void setPublication(String publication) {
		this.publication = publication;
	}


	public Boolean getIsIssued() {
		return isIssued;
	}


	public void setIsIssued(Boolean isIssued) {
		this.isIssued = isIssued;
	}
	
	public String getIssueDate() {
		return issueDate;
	}


	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}


	public String getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}


	public String getIssuerId() {
		return issuerId;
	}


	public void setIssuerId(String issuerId) {
		this.issuerId = issuerId;
	}
	
}
