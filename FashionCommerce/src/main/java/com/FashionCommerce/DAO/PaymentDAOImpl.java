package com.FashionCommerce.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.FashionCommerce.model.PaymentDetails;

public class PaymentDAOImpl implements PaymentDAO{
private static final String url="jdbc:mysql://localhost:3306/eshopping?user=root&password=sayyed";
private static final String insert="insert into paymentdetails (amount, paymentDate, paymentTime, paymentType, customerId, productId,quantity) values (?,?,?,?,?,?,?)";	
@Override
	public int insertPaymentDetails(PaymentDetails paymentDetails) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(insert);
			preparedStatement.setDouble(1, paymentDetails.getAmount());
			preparedStatement.setDate(2, paymentDetails.getPaymentDate());
			preparedStatement.setTime(3, paymentDetails.getPaymentTime());
			preparedStatement.setString(4, paymentDetails.getPaymentType());
			preparedStatement.setInt(5, paymentDetails.getCustomerId());
			preparedStatement.setInt(6, paymentDetails.getProductId());
			preparedStatement.setInt(7, paymentDetails.getQuantity());
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

}
