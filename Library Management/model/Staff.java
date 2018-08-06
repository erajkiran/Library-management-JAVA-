package com.libraryMngt.model;

public class Staff {
	private String id;
	private String fName;
	private String mName;
	private String lName;

	public Staff() {
		this.id = null;
		this.fName = null;
		this.mName = null;
		this.lName = null;
	}

	
	public Staff(String id, String fName, String mName, String lName) {
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
