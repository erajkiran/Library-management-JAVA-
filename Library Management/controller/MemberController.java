package com.libraryMngt.controller;

import java.util.ArrayList;

import com.libraryMngt.model.Book;
import com.libraryMngt.model.BookStorage;
import com.libraryMngt.model.MemberStorage;
import com.libraryMngt.view.MemberView;

public class MemberController {
	static void manageMember(){
		MemberStorage memberStore = null;
		BookStorage bookStore = null;
		
		try
		{
		
			while(true)
			{
				switch(MemberView.showMenu())
				{
				case 1: //Display All Books
					System.out.println("\nAll Books : ");
					System.out.println("\nBookId\tName\tAuthor\tPublication");
					if(bookStore == null)
						bookStore = new BookStorage();
					ArrayList<Book> bookRecords=bookStore.fetchAll();		//function has sql query that returns all data in arraylist
					for(Book i1:bookRecords)
					{
						System.out.print("\n"+i1.getId()+"\t"+i1.getName()+"\t"+i1.getAuthor()+"\t"+i1.getPublication()+"\t");
					}
					break;
					
				case 2: //Search by name
					String bookToSearch = MemberView.SearchBookByName();		//Returns String of book to search for
					
					System.out.println("\nSearched Books : ");
					System.out.println("\nBookId\tName\tAuthor\tPublication");
					if(bookStore == null)
						bookStore = new BookStorage();
					ArrayList<Book> bookRecords1=bookStore.searchBookByName(bookToSearch);		//function has sql query that returns arraylist of data containing give name
					for(Book i1:bookRecords1)
					{
						System.out.print("\n"+i1.getId()+"\t"+i1.getName()+"\t"+i1.getAuthor()+"\t"+i1.getPublication()+"\t");
					}
					break;
					
				case 0:
					return;
				}
			}
		}
		catch(Exception err){
			System.out.println(err.getMessage());
		}
		finally{
			try{
				if(memberStore != null)
					memberStore.close();
			}
			catch(Exception err){
				System.out.println(err.getMessage());
			}
		}	
	}
}
