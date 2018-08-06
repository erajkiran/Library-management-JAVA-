package com.libraryMngt.model;

public class Admin {
	private String id;
	private String fName;
	private String mName;
	private String lName;

	public Admin() {
		this.id = null;
		this.fName = null;
		this.mName = null;
		this.lName = null;
	}

	
	public Admin(String id, String fName, String mName, String lName) {
		this.id = id;
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
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
	
}
