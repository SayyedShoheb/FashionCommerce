package com.FashionCommerce.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.FashionCommerce.model.AdminDetails;
import com.FashionCommerce.model.ProductDetails;

public class AdminDAOImpl implements AdminDAO{
	private static final String url="jdbc:mysql://localhost:3306/eshopping?user=root&password=sayyed";
	private static final String selectAll="select * from admin";
	private static final String adminLogin="select * from admin where emailId=? and password=?";
	@Override
	public List<AdminDetails> getAllAdminDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(selectAll);
			ResultSet resultSet=preparedStatement.executeQuery();
			List<AdminDetails> allAdminDetails=new ArrayList<AdminDetails>();
			if(resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					AdminDetails adminDetails=new AdminDetails();
					
					adminDetails.setEmailId(resultSet.getString("emailId"));
					adminDetails.setPassword(resultSet.getString("password"));
					allAdminDetails.add(adminDetails);	
				
				}
				System.out.println(allAdminDetails);
				return allAdminDetails;
			}
			else {
				System.out.println("Admin details are not inserted yet");
				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	@Override
	public AdminDetails verifyAdminLoginCredentials(String emailId, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(adminLogin);
			preparedStatement.setString(1, emailId);
			preparedStatement.setString(2, password);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				AdminDetails adminDBDetails=new AdminDetails();
				adminDBDetails.setEmailId(resultSet.getString("emailId"));
				adminDBDetails.setPassword(resultSet.getString("password"));
				System.out.println(adminDBDetails);
				return adminDBDetails;
			}
			else {
				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
}

