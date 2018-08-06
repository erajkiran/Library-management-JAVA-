package com.libraryMngt.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.libraryMngt.connectivity.DBConnection;

public class MemberStorage {

	private Connection conn = null;

	public MemberStorage() throws SQLException, Exception{
		conn = DBConnection.getConnection(); 
		
	}
	
	public void add(Member newMember) throws SQLException, Exception{
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			String sql = null;
			sql = "INSERT INTO tblMember (memberId, memberFName, memberMName, memberLName) VALUES (";
			sql += "'" + newMember.getId() + "',";
			sql += "'" + newMember.getfName() + "',";
			sql += "'" + newMember.getmName() + "',";
			sql += "'" + newMember.getlName() + "'";
			sql += ");";
			stmt.execute(sql);
		}
		finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	public Member view(String id) throws SQLException, Exception{

		Statement stmt = null;
		try{
			
			stmt = conn.createStatement();
			String sql = null;
			
			sql = "SELECT * FROM tblMember WHERE memberId='" +id+"';" ;
			Member fetchedMember=new Member();
			ResultSet memberRecord = stmt.executeQuery(sql);
			if(memberRecord.next()){
				String memberId = memberRecord.getString("memberId");
				String fName = memberRecord.getString("memberFName");
				String lName = memberRecord.getString("memberMName");
				String mName = memberRecord.getString("memberLName");
				String book1ID = memberRecord.getString("book1ID");
				String book2ID = memberRecord.getString("book2ID");
				fetchedMember=new Member(memberId,fName,lName,mName,book1ID,book2ID);
			}
			return fetchedMember;
		}
		finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	public boolean delete(String id) throws SQLException, Exception{
		boolean found=true;
		Statement stmt = null;
		try{
			
			stmt = conn.createStatement();
			String sql = null;
			Member checkMember=view(id);
			if(checkMember.getId()==null)
				found=false;
			sql = "DELETE FROM tblMember WHERE memberId='" +id+"';" ;
			stmt.execute(sql);
			return found;
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
	
	
}
