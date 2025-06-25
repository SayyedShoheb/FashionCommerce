package com.FashionCommerce.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.FashionCommerce.model.OrderDetails;

public class OrderDAOImpl implements OrderDAO{
	private static final String url="jdbc:mysql://localhost:3306/eshopping?user=root&password=sayyed";
	private static final String insert="insert into orderDetails (customerId, productId, "
			+ "purchaseQuantity, purchaseDate, totalPurchasePrice) values (?,?,?,?,?)";
	@Override
	public int insertOrderDetails(OrderDetails orderDetails) {
		//insert="insert into orderDetails (customerId, productId, purchaseQuantity, purchaseDate, totalPurchasePrice) 
		//values (?,?,?,?,?)
		LocalDate tdate=LocalDate.now();
		Date date=Date.valueOf(tdate);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(insert);
			preparedStatement.setInt(1,orderDetails.getCustomerId());
			preparedStatement.setInt(2,orderDetails.getProductId());
			preparedStatement.setInt(3, orderDetails.getPuchaseQuantity());
			preparedStatement.setDate(4, date);
			preparedStatement.setDouble(5, orderDetails.getTotoalPurchasePrice());
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
