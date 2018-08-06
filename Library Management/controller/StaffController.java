package com.libraryMngt.controller;

import java.util.ArrayList;

import com.libraryMngt.model.Book;
import com.libraryMngt.model.BookStorage;
import com.libraryMngt.model.Member;
import com.libraryMngt.model.MemberStorage;
import com.libraryMngt.view.StaffView;

public class StaffController {
	static void manageStaff(){
		MemberStorage memberStore = null;
		BookStorage bookStore = null;
		
		try
		{
		
			while(true)
			{
				switch(StaffView.showMenu())
				{
				case 1: //Add New Member
					Member newMember = StaffView.addMember();	//returns Member object to be added in database
					if(memberStore == null)
						memberStore = new MemberStorage();
					memberStore.add(newMember);				//function has sql query that add the collected data
					System.out.println("\nMember Added");
					break;
					
				case 2: //View particular member
					String vmId = StaffView.viewMember(); 	//returns memberID to view for in database
					if(memberStore == null)
						memberStore = new MemberStorage();
					Member fetchedMember= memberStore.view(vmId);		//function has sql query that return the viewing data
					if(fetchedMember.getId()==null)
						System.out.println("Member not found !");
					else
					{
						System.out.println("\nMember : ");
						System.out.println("\nMemberID : "+fetchedMember.getId());
						System.out.println("\nFirst Name : "+fetchedMember.getfName());
						System.out.println("\nMiddle Name : "+fetchedMember.getmName());
						System.out.println("\nLast Name : "+fetchedMember.getlName());
						System.out.println("\nBook1 : "+fetchedMember.getBook1ID());
						System.out.println("\nBook2 : "+fetchedMember.getBook2ID());
						
					}
					break;
					
				case 3: //Delete particular member
					String dmId = StaffView.deleteMember(); 	//returns memberID to view for in database
					if(memberStore == null)
						memberStore = new MemberStorage();
					if(memberStore.delete(dmId))					//function has sql query that deletes the passed data and returns boolean value accordingly
						System.out.println("\nMember Deleted");
					else
						System.out.println("\nMember not found !");
					break;
					
				case 4: //Issue Book
					String bookandissuerId = StaffView.issueBook();		//returns bookID and issuerID
					String bookId ="",issuerId ="";
					int i =0;
					while(bookandissuerId.charAt(i) != '*'){
						bookId=bookId + bookandissuerId.charAt(i);
						i++;
					}
					i++;
					while(i<bookandissuerId.length()){
						issuerId= issuerId+ bookandissuerId.charAt(i);
						i++;
					}
					
					System.out.println(bookandissuerId+" book : "+bookId+" isur : "+issuerId);
					if(bookStore == null)
						bookStore = new BookStorage();
					
					if(memberStore == null)
						memberStore = new MemberStorage();

					if(bookStore.isIssuable(bookId)){						//check if book brought is not already issued
						//at this point book is issuable and is not issued by someone else
						
//						System.out.println("issuable");
						Member checkMember = memberStore.view(issuerId);
//						System.out.println(checkMember.getBook1ID()+"* "+ checkMember.getBook2ID()+"*");
						if(checkMember.getBook1ID()==null||checkMember.getBook1ID().equals("")) 
						{
//							System.out.println("slot1");
							
							bookStore.issueBook(bookId,issuerId,1);	
						}
						else if(checkMember.getBook2ID()==null||checkMember.getBook2ID().equals(""))
						{
//							System.out.println("slot2");
							
							bookStore.issueBook(bookId,issuerId,2);
						}
						else{
							//this means that none slot(of two books) is empty	
							System.out.println("Sorry only two books can be issued ! ");
						}
					}
					else
						System.out.println("\nSorry this book is already issued !");
					
					break;
					
				case 5: //Return Book
					String returnId = StaffView.returnBook();		//returns bookID
					if(bookStore == null)
						bookStore = new BookStorage();
					
					if( !bookStore.isIssuable(returnId)){		//book must be issued while returning so isIssuable will give false
						int fineAmt = bookStore.returnBook(returnId);		//returns amount as fine to be collected	
						System.out.println("\nBook Returned Successfully!\nCollect Fine Rs. = "+fineAmt);
					}
					else
						System.out.println("\nBook is not Issued . So it cannot be returned .");
					break;
					
				case 6: //Add Book
					Book newBook = StaffView.addBook();	//returns Book object to be added in database
					if(bookStore == null)
						bookStore = new BookStorage();
					bookStore.add(newBook);					//function has sql query that add the collected data
					System.out.println("\nBook Added");
					break;
					
				case 7: //View Book
					String vbId = StaffView.viewBook(); 	//returns bookID to view for in database
					if(bookStore == null)
						bookStore = new BookStorage();
					Book fetchedBook= bookStore.view(vbId);			//function has sql query that return the viewing data
					if(fetchedBook.getId()==null)
						System.out.println("Book not Found !");
					else{
						System.out.println("\nBook : ");
						System.out.println("\nBookID : "+fetchedBook.getId());
						System.out.println("\nName : "+fetchedBook.getName());
						System.out.println("\nAuthor : "+fetchedBook.getAuthor());
						System.out.println("\nPublication : "+fetchedBook.getPublication());
						if(fetchedBook.getIsIssued())
							System.out.println("\nisIssued : Yes");
						else
							System.out.println("\nisIssued : No");
						
						System.out.println("\nIssue Date : "+fetchedBook.getIssueDate());
						System.out.println("\nReturn Date : "+fetchedBook.getReturnDate());
						System.out.println("\nIssuer Id : "+fetchedBook.getIssuerId());
					}
					break;
					
				case 8: //Delete Book
					String dbId = StaffView.deleteBook(); 	//returns bookID to view for in database
					if(bookStore == null)
						bookStore = new BookStorage();
					if(bookStore.delete(dbId))						//function has sql query that deletes the passed data and returns boolean value accordingly
						System.out.println("\nBook Deleted");
					else
						System.out.println("\nBook not found !");
					
					break;
					
				case 9: //Display All Books
					System.out.println("\nAll Books : ");
					System.out.println("\nBookId\tName\tAuthor\tPublication\tIsIssued\tIssueDate\tReturnDate\tIssuerID");
					if(bookStore == null)
						bookStore = new BookStorage();
					ArrayList<Book> bookRecords=bookStore.fetchAll();		//function has sql query that returns all data in arraylist
					for(Book i1:bookRecords)
					{
						System.out.print("\n"+i1.getId()+"\t"+i1.getName()+"\t"+i1.getAuthor()+"\t"+i1.getPublication()+"\t");
						if(i1.getIsIssued())
							System.out.print("Yes\t");
						else
							System.out.print("No\t");
						System.out.println(i1.getIssueDate()+"\t"+i1.getReturnDate()+"\t"+i1.getIssuerId());
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
