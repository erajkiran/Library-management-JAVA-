package com.libraryMngt.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import com.libraryMngt.connectivity.DBConnection;

public class BookStorage {

	private Connection conn = null;

	public BookStorage() throws SQLException, Exception{
		conn = DBConnection.getConnection(); 
		
	}
	
	public void add(Book newBook) throws SQLException, Exception{

		Statement stmt = null;
		try{
			
			stmt = conn.createStatement();
			String sql = null;
			
			//for new book by default isIssued is No and issueDate and returnDate are blank
			sql = "INSERT INTO tblBook (bookId, name, author, publication) VALUES (";
			sql += "'" + newBook.getId() + "',";
			sql += "'" + newBook.getName()+ "',";
			sql += "'" + newBook.getAuthor() + "',";
			sql += "'" + newBook.getPublication()+ "'";
			sql += ");";
			
			stmt.execute(sql);
		}
		finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	public Book view(String id) throws SQLException, Exception{

		Statement stmt = null;
		try{
			
			stmt = conn.createStatement();
			String sql = null;
			
			sql = "SELECT * FROM tblBook WHERE bookId='" +id+"';" ;
			Book fetchedBook=new Book();
			ResultSet bookRecord = stmt.executeQuery(sql);
			if(bookRecord.next()){
				String bookId = bookRecord.getString("bookId");
				String name= bookRecord.getString("name");
				String author = bookRecord.getString("author");
				String publication = bookRecord.getString("publication");
					String isIssuedstr = bookRecord.getString("isIssued");
					Boolean isIssued=false;
					if(isIssuedstr.equals("Yes"))
						isIssued=true;
					else if(isIssuedstr.equals("No"))
						isIssued=false;
				
				String issueDate = bookRecord.getString("issueDate");
				String returnDate = bookRecord.getString("returnDate");
				String issuerId = bookRecord.getString("issuerId");
				fetchedBook=new Book(bookId,name,author,publication,isIssued,issueDate,returnDate,issuerId);			
			}
			return fetchedBook;
		}
		finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	public boolean delete(String id) throws SQLException, Exception{

		boolean found =true;
		Statement stmt = null;
		try{
			
			stmt = conn.createStatement();
			String sql = null;
			
			Book checkBook=view(id);
			if(checkBook.getId()==null)
				found=false;
			
			sql = "DELETE FROM tblBook WHERE bookId='" +id+"';" ;
			stmt.execute(sql);
			return found;
		}
		finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	public ArrayList<Book> fetchAll() throws  SQLException, Exception{
		   
		Statement stmt = null;
		try{
			
			stmt = conn.createStatement();
			String sql = null;
			
			sql = "SELECT * FROM tblBook ;" ;
			
			ResultSet bookRecords = stmt.executeQuery(sql);
			
			ArrayList<Book> books = new ArrayList<Book>();
			while(bookRecords.next()){
				String bookId = bookRecords.getString("bookId");
				String name= bookRecords.getString("name");
				String author = bookRecords.getString("author");
				String publication = bookRecords.getString("publication");
					String isIssuedstr = bookRecords.getString("isIssued");
					Boolean isIssued=false;
					if(isIssuedstr.equals("Yes"))
						isIssued=true;
					else if (isIssuedstr.equals("No"))
						isIssued=false;
				
				String issueDate = bookRecords.getString("issueDate");
				String returnDate = bookRecords.getString("returnDate");
				String issuerId = bookRecords.getString("issuerId");
				
				books.add(new Book(bookId,name,author,publication,isIssued,issueDate,returnDate,issuerId));
			}
			return books;
		}
		finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	public void close() throws SQLException, Exception{
		if(conn != null)
			conn.close();
	}

	public boolean isIssuable(String issueId) throws SQLException, Exception {
		
		Book checkBook=view(issueId);
		if(checkBook.getIsIssued())		//if book is already issued
			return false;				//then you cannot issue it
		else
			return true;				//else you can issue it
	}

	public void issueBook(String issueId,String issuerId,int slot) throws SQLException {
		Statement stmt = null;
		try{	
			stmt = conn.createStatement();
			String sql = null;		//for updating book table
			String sql1 = null;		//for updating member table
			
			LocalDate localDate = LocalDate.now();
	        String currDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate);
	        
			sql = "UPDATE tblBook SET isIssued='Yes',issueDate='"+currDate+"',returnDate=NULL,issuerId='"+issuerId+"' WHERE bookId='"+issueId+"' ;" ;
			if(slot==1)
				sql1 = "UPDATE tblMember SET book1ID='"+issueId+"' WHERE memberId='"+issuerId+"' ;" ;
			else if(slot==2)
				sql1 = "UPDATE tblMember SET book2ID='"+issueId+"' WHERE memberId='"+issuerId+"' ;" ;
			
			
			stmt.execute(sql);
			stmt.execute(sql1);
			
		}
		finally{
			if(stmt != null)
				stmt.close();
		}
	}

	public int returnBook(String returnId) throws SQLException, Exception {
		Statement stmt = null;
		
		int fineAmt=0;
		
		Book returnBook=view(returnId);
		
		Member newMember=null;
		
		//if memberId has f or F at first character then it is faculty 
		
		if(returnBook.getIssuerId().charAt(0)=='f' || returnBook.getIssuerId().charAt(0)=='F')
			newMember = new FacultyMember();
		else if(returnBook.getIssuerId().charAt(0)=='s'||returnBook.getIssuerId().charAt(0)=='S')
			newMember = new StudentMember();
		
		
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		String issueDateStr=returnBook.getIssueDate();
		
		LocalDate localDate = LocalDate.now();
        String currDateStr = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDate);
		
        Date issueDate = myFormat.parse(issueDateStr);
        Date currDate=myFormat.parse(currDateStr);
        
        int days=(int) (currDate.getTime()-issueDate.getTime());
        
        //at this point days is in milliseconds to convert it to days we will divide by no of milliseconds in one day        
        
        days=days/(24*60*60*1000);
        
        if(newMember instanceof FacultyMember){		//s or S in issuerId Stands for student and fine charge is 5rs/day	
        	if(days>FacultyMember.getNoOfDays())
        		fineAmt =(int) ( FacultyMember.getFineAmt()*(days-FacultyMember.getNoOfDays()));
        	else
        		fineAmt=0;
        }
        else if(newMember instanceof StudentMember){		//f or F in issuerId Stands for Faculty and fine charge is 2rs/day 
        	if(days>StudentMember.getNoOfDays())
        		fineAmt =(int) ( StudentMember.getFineAmt()*(days-StudentMember.getNoOfDays()));
        	else
        		fineAmt=0;
        }
        
        
        
        stmt = conn.createStatement();
		String sql = null;
		
		sql = "UPDATE tblBook SET isIssued='No',returnDate='"+currDateStr+"' WHERE bookId='"+returnId+"' ;" ;
		
		stmt.execute(sql);
		
		return fineAmt;
	}
	
	public ArrayList<Book> searchBookByName(String bookToSearch) throws  SQLException, Exception{
		   
		Statement stmt = null;
		try{
			
			stmt = conn.createStatement();
			String sql = null;
			
			sql = "SELECT * FROM tblBook where name='"+bookToSearch+"';" ;
			
			ResultSet bookRecords = stmt.executeQuery(sql);
			
			ArrayList<Book> books = new ArrayList<Book>();
			while(bookRecords.next()){
				String bookId = bookRecords.getString("bookId");
				String name= bookRecords.getString("name");
				String author = bookRecords.getString("author");
				String publication = bookRecords.getString("publication");
					String isIssuedstr = bookRecords.getString("isIssued");
					Boolean isIssued=false;
					if(isIssuedstr.equalsIgnoreCase("yes"));
						isIssued=true;
				
				String issueDate = bookRecords.getString("issueDate");
				String returnDate = bookRecords.getString("returnDate");
				String issuerId = bookRecords.getString("issuerId");
				
				books.add(new Book(bookId,name,author,publication,isIssued,issueDate,returnDate,issuerId));
			}
			return books;
		}
		finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	
}
