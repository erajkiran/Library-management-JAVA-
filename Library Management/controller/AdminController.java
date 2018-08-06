package com.libraryMngt.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.libraryMngt.model.AdminStorage;
import com.libraryMngt.model.FacultyMember;
import com.libraryMngt.model.Staff;
import com.libraryMngt.model.StaffStorage;
import com.libraryMngt.model.StudentMember;
import com.libraryMngt.view.AdminView;

public class AdminController {
	static void manageAdmin(){
		AdminStorage adminStore = null;
		StaffStorage staffStore = null;
		
		try
		{
		
			while(true)
			{
				switch(AdminView.showMenu())
				{
				case 1: //Add New Staff
					Staff newStaff = AdminView.addStaff();	//returns Staff object to be added in database
					if(staffStore == null)
						staffStore = new StaffStorage();		
					
					staffStore.add(newStaff);					//function has sql query that add the collected data
					System.out.println("\nStaff Added");
					break;
					
				case 2: //View particular staff
					String vId = AdminView.viewStaff(); 	//returns staffID to view for in database
					if(staffStore == null)
						staffStore = new StaffStorage();
					Staff fetchedStaff= staffStore.view(vId);		//function has sql query that return the viewing data
					if(fetchedStaff.getId()==null)
						System.out.println("\nStaff not Found !");
					else{
						System.out.println("\nStaff : ");
						System.out.println("\nStaffID : "+fetchedStaff.getId());
						System.out.println("\nFirst Name : "+fetchedStaff.getfName());
						System.out.println("\nMiddle Name : "+fetchedStaff.getmName());
						System.out.println("\nLast Name : "+fetchedStaff.getlName());
					}
					break;
				case 3: //Delete particular staff
					String dId = AdminView.deleteStaff(); 	//returns staffID to view for in database
					if(staffStore == null)
						staffStore = new StaffStorage();
					
					if(staffStore.delete(dId))					//function has sql query that deletes the passed data and returns boolean value accordingly
						System.out.println("\nStaff Deleted");
					else
						System.out.println("\nStaff not found");
						
					break;
				case 4: //Display all staff
					System.out.println("\nAll Staff Members : ");
					System.out.println("\nStaffID\tFName\tMName\tLName");
					if(staffStore == null)
						staffStore = new StaffStorage();
					ArrayList<Staff> staffRecords=staffStore.fetchAll();		//function has sql query that returns all data in arraylist
						
					for(Staff i:staffRecords)
					{
						System.out.println("\n"+i.getId()+"\t"+i.getfName()+"\t"+i.getmName()+"\t"+i.getlName()+"\t");
					}
					break;
				
				case 5://change faculty fine amt
					int newFineFaculty=AdminView.changeFacultyFine();
					FacultyMember.setFineAmt(newFineFaculty);
					break;
					
				case 6://change student fine amt
					int newFineStudent=AdminView.changeStudentFine();
					StudentMember.setFineAmt(newFineStudent);
					break;
					
				case 7://Change no of days book can be issued by Faculty without fine 
					int newDaysFaculty=AdminView.changeFacultyDays();
					FacultyMember.setNoOfDays(newDaysFaculty);
					break;
				case 8://change student fine amt
					int newDaysStudent=AdminView.changeStudentDays();
					StudentMember.setNoOfDays(newDaysStudent);
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
				if(adminStore != null)
					adminStore.close();
			}
			catch(Exception err){
				System.out.println(err.getMessage());
			}
		}
		
	}
		
}
