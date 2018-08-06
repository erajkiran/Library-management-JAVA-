package com.libraryMngt.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.libraryMngt.connectivity.DBConnection;

public class AdminStorage {

	private Connection conn = null;

	public AdminStorage() throws SQLException, Exception{
		conn = DBConnection.getConnection(); 
		
	}
	
	public void add(Admin newAdmin) throws SQLException, Exception{

		Statement stmt = null;
		try{
			
			stmt = conn.createStatement();
			String sql = null;
			
			sql = "INSERT INTO 'tblAdmin' ('adminId', 'adminFName', 'adminMName', 'adminLName') VALUES (";
			sql += "'" + newAdmin.getId() + "',";
			sql += "'" + newAdmin.getfName() + "',";
			sql += "'" + newAdmin.getmName() + "',";
			sql += "'" + newAdmin.getlName() + "'";
			sql += ")";
			
			
			stmt.execute(sql);
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
