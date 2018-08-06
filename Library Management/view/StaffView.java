package com.libraryMngt.view;

import java.util.Scanner;

import com.libraryMngt.model.Book;
import com.libraryMngt.model.FacultyMember;
import com.libraryMngt.model.Member;
import com.libraryMngt.model.StudentMember;

public class StaffView {

	public static int showMenu(){
		Scanner input = new Scanner(System.in);
		int choice;
		
		while(true){
			System.out.println("\n\nStaff Management:\n");
			System.out.println("1. Add Member");
			System.out.println("2. View Member");
			System.out.println("3. Delete Member");
			System.out.println("4. Issue Book");
			System.out.println("5. Return Book");
			System.out.println("6. Add Book");
			System.out.println("7. View Book");
			System.out.println("8. Delete Book");
			System.out.println("9. View All Books");
			System.out.println("0. Exit");
			System.out.println("Enter your choice: ");
			choice = input.nextInt();
			
			if(choice >= 0 && choice <= 9)
				return choice;
			else
				System.out.println("\nInvalid Choice!\n");
		}
	}

	public static Member addMember(){
		Scanner input = new Scanner(System.in);
		String id, fName, mName, lName;
		System.out.print("\n\nEnter Member Id: ");
		id = input.next();
		System.out.print("\n\nEnter Member First Name: ");
		fName = input.next();
		System.out.print("\n\nEnter Member Middle Name: ");
		mName = input.next();
		System.out.print("\n\nEnter Member Last Name: ");
		lName = input.next();
		Member newMember = new Member(id,fName, mName, lName);
		return newMember ;
	}
	
	public static String viewMember(){
		Scanner input = new Scanner(System.in);
		String staffID;
		
		System.out.print("\n\nEnter Member Id: ");
		staffID = input.next();
		
		return staffID;
	}
	public static String deleteMember(){
		Scanner input = new Scanner(System.in);
		String staffID;
		
		System.out.print("\n\nEnter Member Id: ");
		staffID = input.next();
		
		return staffID;
	}
	public static String issueBook(){					//function sends book and issuer id separated by * in bookid*issuerid format
		Scanner input = new Scanner(System.in);
		String bookID;
		String issuerID;
		
		System.out.print("\n\nEnter Book Id: ");
		bookID = input.next();
		System.out.print("\n\nEnter Issuer Id: ");
		issuerID = input.next();
		
		String bookandissuerID=bookID+"*"+issuerID;
		return bookandissuerID;
	}
	public static String returnBook(){
		Scanner input = new Scanner(System.in);
		String bookID;
		
		System.out.print("\n\nEnter Book Id: ");
		bookID = input.next();
		
		return bookID;
	}
	
	public static Book addBook(){
		Scanner input = new Scanner(System.in);
		String id;
		String name;
		String author;
		String publication;
		//condition for newly added book as follows
		Boolean isIssued=false;
		String issueDate=null;
		String returnDate=null;
		String issuerId=null;
		
		System.out.print("\n\nEnter Book Id: ");
		id = input.next();
		System.out.print("\n\nEnter Book Name: ");
		name = input.next();
		System.out.print("\n\nEnter Book Author: ");
		author = input.next();
		System.out.print("\n\nEnter Book Publication: ");
		publication = input.next();
		
		Book newBook = new Book(id,name, author, publication, isIssued,issueDate,returnDate,issuerId);
		return newBook ;
	}
	public static String viewBook(){
		Scanner input = new Scanner(System.in);
		String bookID;
		
		System.out.print("\n\nEnter Book Id: ");
		bookID = input.next();
		
		return bookID;
	}
	public static String deleteBook(){
		Scanner input = new Scanner(System.in);
		String bookID;
		
		System.out.print("\n\nEnter Book Id: ");
		bookID = input.next();
		
		return bookID;
	}
	
	
	
	
	

}//Class End
