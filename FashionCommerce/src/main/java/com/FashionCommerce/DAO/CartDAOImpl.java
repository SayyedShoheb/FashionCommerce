package com.FashionCommerce.DAO;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.FashionCommerce.model.Cart;
import com.FashionCommerce.model.ProductDetails;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class CartDAOImpl implements CartDAO{
	private static final String url="jdbc:mysql://localhost:3306/eshopping?user=root&password=sayyed";
	private static final String productsCount="select count(customerId) from cart where customerId=?";
	private static final String insertProduct="insert into cart (customerId, productId) values (?,?)";
	private static final String selectAll="select * from cart where customerId=? and productId=?";
	private static final String selectAllProductsFromCart="select * from productdetails join"
			+ " cart on cart.productId=productdetails.id where customerId=?";
	@Override
	public int productsCountInCart(int customerId) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(productsCount);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				ResultSetMetaData metaData=(ResultSetMetaData)resultSet.getMetaData();
			return resultSet.getInt(1);
			}else {
				return 0;
			}
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
	public int insertProductInCart(Cart cart) {
		//insertProduct="insert into cart (customerId, productId) values (?,?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(insertProduct);
			preparedStatement.setInt(1, cart.getCustomerId());
			preparedStatement.setInt(2, cart.getProductId());
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
	public boolean getDetailsOfTheProduct(int customerId,int productId) {
		// String selectAll="select * from cart where customerId=? and productId=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(selectAll);
			preparedStatement.setInt(1, customerId);
			preparedStatement.setInt(2, productId);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
					return true;
				}
				
			else {
				return false;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	public List<ProductDetails>  getAllCartProducts(int customerId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(selectAllProductsFromCart);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst()) {
				List<ProductDetails> list =new ArrayList<ProductDetails>();
				while(resultSet.next()) {
					ProductDetails productDetails=new ProductDetails();
					productDetails.setId(resultSet.getInt("id"));
					productDetails.setName(resultSet.getString("name"));
					productDetails.setBrand(resultSet.getString("brand"));
					productDetails.setPrice(resultSet.getDouble("price"));
					productDetails.setDiscount(resultSet.getDouble("discount"));
					productDetails.setCategory(resultSet.getString("category"));
					productDetails.setQuantity(resultSet.getInt("quantity"));
					productDetails.setImageaddress(resultSet.getString("imageaddress"));
					list.add(productDetails);
				}
				return list;
			}else {
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

