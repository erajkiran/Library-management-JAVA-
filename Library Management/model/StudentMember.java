package com.libraryMngt.model;

public class StudentMember extends Member{
	
	private static int fineAmt;
	private static int noOfDays;		//no of days member can hold book after fine starts
	
	static{
		fineAmt=5;
		noOfDays=10;
	}
	
	public StudentMember() {
		super();
	}
	
	public StudentMember(String id, String fName, String mName, String lName) {	
		super(id,fName,mName,lName);
		
	}
	
	public StudentMember(String id, String fName, String mName, String lName,String book1ID,String book2ID) {
		super(id,fName,mName,lName,book1ID,book2ID);
	}
	
	
	public static int getNoOfDays() {
		return noOfDays;
	}

	public static void setNoOfDays(int noOfDays) {
		StudentMember.noOfDays = noOfDays;
	}

	public static int getFineAmt() {
		return fineAmt;
	}

	public static void setFineAmt(int fineAmt) {
		StudentMember.fineAmt = fineAmt;
	}

}
