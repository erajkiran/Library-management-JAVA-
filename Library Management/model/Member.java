package com.libraryMngt.model;

public class Member {
	private String id;
	private String fName;
	private String mName;
	private String lName;
	private String book1ID;
	private String book2ID;

	public Member() {
		this.id = null;
		this.fName = null;
		this.mName = null;
		this.lName = null;
		this.book1ID=null;
		this.book2ID=null;		
	}

	public Member(String id, String fName, String mName, String lName) {
		this.id = id;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.book1ID = null;
		this.book2ID = null;
	}
	
	public Member(String id, String fName, String mName, String lName,String book1ID,String book2ID) {
		this.id = id;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.book1ID = book1ID;
		this.book2ID = book2ID;
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getmName() {
		return mName;
	}


	public void setmName(String mName) {
		this.mName = mName;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}


	public String getBook1ID() {
		return book1ID;
	}


	public void setBook1ID(String book1id) {
		book1ID = book1id;
	}


	public String getBook2ID() {
		return book2ID;
	}


	public void setBook2ID(String book2id) {
		book2ID = book2id;
	}
	
}
