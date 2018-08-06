package com.libraryMngt.model;

public class FacultyMember extends Member{
	
	private static int fineAmt;
	private static int noOfDays;		//no of days member can hold book after fine starts
	static{
		fineAmt=2;
		noOfDays=15;
	}
	
	public FacultyMember() {
		super();
	}
	
	public FacultyMember(String id, String fName, String mName, String lName) {
		
		super(id,fName,mName,lName);
	}
	
	public FacultyMember(String id, String fName, String mName, String lName,String book1ID,String book2ID) {
		super(id,fName,mName,lName,book1ID,book2ID);
	}
	
	public static int getNoOfDays() {
		return noOfDays;
	}

	public static void setNoOfDays(int noOfDays) {
		FacultyMember.noOfDays = noOfDays;
	}

	public static int getFineAmt() {
		return fineAmt;
	}

	public static void setFineAmt(int fineAmt) {
		FacultyMember.fineAmt = fineAmt;
	}

}
