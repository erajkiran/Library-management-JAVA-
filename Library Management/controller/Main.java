package com.libraryMngt.controller;

import java.util.Scanner;

import com.libraryMngt.view.MainView;

public class Main {

	public static void main(String[] args) {
		while(true){
			switch(MainView.mainMenu())
			{
				case 1: //Admin
					AdminController.manageAdmin();
					break;
					
				case 2: //Staff
					StaffController.manageStaff();
					
					break;
					
				case 3: //Members
					MemberController.manageMember();
					
					break;
					
				case 0: //Exit
					System.out.println("\nBye...!");
					return;			
			}
		}
	}

}
