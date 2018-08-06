package com.libraryMngt.view;

import java.util.Scanner;

public class MainView {

	public static int mainMenu(){

		int choice;
		Scanner input = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("\n\nMain Menu:\n");
			System.out.println("1. Admin ");
			System.out.println("2. Staff");
			System.out.println("3. Member");
			System.out.println("0. Exit");
			System.out.println("Enter your choice: ");
			choice = input.nextInt();
		
			if(choice >=0 && choice <= 3)
				return choice;
			else
				System.out.println("\n\nInvalid Choice:\n");
		
		}
	}
	
	
}
