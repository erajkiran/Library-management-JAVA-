package com.libraryMngt.view;

import java.util.Scanner;

import com.libraryMngt.model.Staff;

public class AdminView {

	public static int showMenu(){
		Scanner input = new Scanner(System.in);
		int choice;
		
		while(true){
			System.out.println("\n\nAdmin Management:\n");
			System.out.println("1. Add Staff");
			System.out.println("2. View Staff");
			System.out.println("3. Delete Staff");
			System.out.println("4. Display All Staff");
			System.out.println("5. Change Faculty Fine Amt");
			System.out.println("6. Change Student Fine Amt");
			System.out.println("7. Change no of days book can be issued by Faculty without fine : ");
			System.out.println("8. Change no of days book can be issued by Student without fine : ");
			
			System.out.println("0. Exit");
			System.out.println("Enter your choice: ");
			choice = input.nextInt();
			
			if(choice >= 0 && choice <= 8)
				return choice;
			else
				System.out.println("\nInvalid Choice!\n");
		}
	}

	public static Staff addStaff(){
		Scanner input = new Scanner(System.in);
		String id, fName, mName, lName;
		
		System.out.print("\n\nEnter Staff Id: ");
		id = input.next();
		System.out.print("\n\nEnter Staff First Name: ");
		fName = input.next();
		System.out.print("\n\nEnter Staff Middle Name: ");
		mName = input.next();
		System.out.print("\n\nEnter Staff Last Name: ");
		lName = input.next();
		
		Staff newStaff = new Staff(id,fName, mName, lName);
		return newStaff ;
	}
	public static String viewStaff(){
		Scanner input = new Scanner(System.in);
		String staffID;
		
		System.out.print("\n\nEnter Staff Id: ");
		staffID = input.next();
		
		return staffID;
	}
	public static String deleteStaff(){
		Scanner input = new Scanner(System.in);
		String staffID;
		
		System.out.print("\n\nEnter Staff Id: ");
		staffID = input.next();
		
		return staffID;
	}
	
	public static int changeFacultyFine() {
		int newFineFaculty=0;
		System.out.println("Enter new Faculty fine amt : ");
		Scanner input = new Scanner(System.in);
		newFineFaculty=input.nextInt();
		
		return newFineFaculty;
	}
	public static int changeStudentFine() {
		int newFineStudent=0;
		System.out.println("Enter new Student fine amt : ");
		Scanner input = new Scanner(System.in);
		newFineStudent=input.nextInt();
		
		return newFineStudent;
	}
	public static int changeFacultyDays() {
		int newDaysFaculty=0;
		System.out.println("Enter new no of days book can be issued by Faculty without fine : ");
		Scanner input = new Scanner(System.in);
		newDaysFaculty=input.nextInt();
		
		return newDaysFaculty;
	}
	public static int changeStudentDays() {
		int newDaysStudent=0;
		System.out.println("Enter new no of days book can be issued by Student without fine : ");
		Scanner input = new Scanner(System.in);
		newDaysStudent=input.nextInt();
		
		return newDaysStudent;
	}

}//Class End
