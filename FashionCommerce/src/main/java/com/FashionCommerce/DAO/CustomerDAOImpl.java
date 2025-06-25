package com.FashionCommerce.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.FashionCommerce.model.CustomerDetails;

public class CustomerDAOImpl implements CustomerDAO{
	
	private static final String url="jdbc:mysql://localhost:3306/eshopping?user=root&password=sayyed";
	private static final String insert="insert into customerdetails (name, emailId, mobileNumber, gender, address, password) values (?,?,?,?,?,?)";
	private static final String selectAll="select * from customerdetails";
	private static final String loginQuery="select * from customerdetails where emailId=? and password=?";
	@Override
	public int insertCustomerDetails(CustomerDetails customerDetails) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(insert);
			preparedStatement.setString(1, customerDetails.getName());
			preparedStatement.setString(2, customerDetails.getEmailId());
			preparedStatement.setLong(3, customerDetails.getMobileNumber());
			preparedStatement.setString(4, customerDetails.getGender());
			preparedStatement.setString(5, customerDetails.getAddress());
			preparedStatement.setString(6, customerDetails.getPassword());
			return preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	@Override
	public List <CustomerDetails> getAllCustomerDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(selectAll);
			ResultSet resultSet=preparedStatement.executeQuery();
			List <CustomerDetails> list=new ArrayList<CustomerDetails>();
			if(resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
				CustomerDetails customerDetails=new CustomerDetails();
				customerDetails.setEmailId(resultSet.getString("emailId"));
				customerDetails.setMobileNumber(resultSet.getLong("mobileNumber"));
				customerDetails.setPassword(resultSet.getString("password"));
				list.add(customerDetails);
			}
				return list;
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
	@Override
	public CustomerDetails getLoginCustomerDetails(String emailId,String password) {
		//loginQuery="select * from customerdetails where emailId=? and password=?"
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(loginQuery);
			preparedStatement.setString(1, emailId);
			preparedStatement.setString(2, password);
			ResultSet resultSet= preparedStatement.executeQuery();
			if(resultSet.next()) {
				CustomerDetails customerDetails=new CustomerDetails();
				customerDetails.setId(resultSet.getInt("id"));
				customerDetails.setName(resultSet.getString("name"));
				customerDetails.setEmailId(resultSet.getString("emailId"));
				customerDetails.setMobileNumber(resultSet.getLong("mobileNumber"));
				customerDetails.setGender(resultSet.getString("gender"));
				customerDetails.setAddress(resultSet.getString("address"));
				customerDetails.setPassword(resultSet.getString("password"));
				return customerDetails;
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

