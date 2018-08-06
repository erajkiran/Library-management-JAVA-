package com.libraryMngt.view;

import java.util.Scanner;

import com.libraryMngt.model.Staff;

public class MemberView {

	public static int showMenu(){
		Scanner input = new Scanner(System.in);
		int choice;
		
		while(true){
			System.out.println("\n\nMember Management:\n");
			System.out.println("1. View All Books");
			System.out.println("2. Search by book name");
			System.out.println("0. Exit");
			System.out.println("Enter your choice: ");
			choice = input.nextInt();
			
			if(choice >= 0 && choice <= 2)
				return choice;
			else
				System.out.println("\nInvalid Choice!\n");
		}
	}

	public static String SearchBookByName(){
		Scanner input = new Scanner(System.in);
		String bookToSearch;
		
		System.out.print("\n\nEnter Book Name to search : ");
		bookToSearch= input.next();
		
		return bookToSearch;
	}
	
	

}//Class End
