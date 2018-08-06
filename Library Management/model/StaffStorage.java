package com.libraryMngt.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.libraryMngt.connectivity.DBConnection;

public class StaffStorage {

	private Connection conn = null;

	public StaffStorage() throws SQLException, Exception{
		conn = DBConnection.getConnection(); 
	}
	
	public void add(Staff newStaff) throws SQLException, Exception{

		Statement stmt = null;
		try{
			
			stmt = conn.createStatement();
			String sql = null;
			
			sql = "INSERT INTO tblstaff (staffId, staffFName, staffMName, staffLName) VALUES (";
			sql += "'" + newStaff.getId() + "',";
			sql += "'" + newStaff.getfName() + "',";
			sql += "'" + newStaff.getmName() + "',";
			sql += "'" + newStaff.getlName() + "'";
			sql += ");";
			stmt.execute(sql);
		}
		finally{
			if(stmt != null)
				stmt.close();
		}
	}
	
	public Staff view(String id) throws SQLException, Exception{

		Statement stmt = null;
		try{
			
			stmt = conn.createStatement();
			String sql = null;
			
			sql = "SELECT * FROM tblstaff WHERE staffId='" +id+"';" ;
			ResultSet staffRecord = stmt.executeQuery(sql);
			
			Staff fetchedStaff=new Staff();
			if(staffRecord.next()){
				String staffId = staffRecord.getString("staffId");
				String fName = staffRecord.getString("staffFName");
				String mName = staffRecord.getString("staffMName");
				String lName = staffRecord.getString("staffLName");
				fetchedStaff=new Staff(staffId,fName,lName,mName);
			}
			return fetchedStaff;
			
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
			Staff checkstaff=view(id);
			if(checkstaff.getId()==null)
				found=false;
			sql = "DELETE FROM tblStaff WHERE staffId='" +id+"';" ;
			stmt.execute(sql);
			return found;
		}
		finally{
			if(stmt != null)
				stmt.close();
		}
		
	}
	
	public ArrayList<Staff> fetchAll() throws  SQLException, Exception{
		   
		Statement stmt = null;
		try{
			
			stmt = conn.createStatement();
			String sql = null;
			
			sql = "SELECT * FROM tblStaff ;" ;
			
			ResultSet staffRecords = stmt.executeQuery(sql);
			
			ArrayList<Staff> staffs = new ArrayList<Staff>();
			while(staffRecords.next()){
				String staffId = staffRecords.getString("staffId");
				String fName = staffRecords.getString("staffFName");
				String mName = staffRecords.getString("staffMName");
				String lName = staffRecords.getString("staffLName");
				staffs.add(new Staff(staffId, fName, mName , lName ));
			}
			return staffs;
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
