package com.FashionCommerce.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.FashionCommerce.model.ProductDetails;

public class ProductDAOImpl implements ProductDAO{

	private static final String addProduct="insert into productdetails (name, brand, price, discount, category, quantity,imageaddress) values (?,?,?,?,?,?,?)";
	private static final String deleteProduct="delete from productdetails where id=?";
	private static final String url="jdbc:mysql://localhost:3306/eshopping?user=root&password=sayyed";
	private static final String selectAllProducts="select * from productdetails";
	private static final String searchByQuery = "select * from productdetails where name=? || brand=? ||category=?";

	@Override
	public int addProduct(ProductDetails productDetail) {
		try {
			//id, name, brand, price, discount, category, quantity
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(addProduct);
			preparedStatement.setString(1, productDetail.getName());
			preparedStatement.setString(2, productDetail.getBrand());
			preparedStatement.setDouble(3, productDetail.getPrice());
			preparedStatement.setDouble(4, productDetail.getDiscount());
			preparedStatement.setString(5, productDetail.getCategory());
			preparedStatement.setInt(6, productDetail.getQuantity());
			preparedStatement.setString(7, productDetail.getImageaddress());
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
	public int deleteProduct(int productid) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(deleteProduct);
			preparedStatement.setInt(1, productid);
			return preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public List<ProductDetails>  getAllProductsDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(selectAllProducts);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst()) {
				List<ProductDetails> list = new ArrayList<ProductDetails>();
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
	public List<ProductDetails> filterAllProductDetails(String filterBy) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/eshopping?user=root&password=sayyed");
			// searchByQuery = "select * from productdetails where name=? || brand=? ||category=?";
			PreparedStatement preparedStatement = connection.prepareStatement(searchByQuery);
			preparedStatement.setString(1, filterBy);
			preparedStatement.setString(2, filterBy);
			preparedStatement.setString(3, filterBy);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<ProductDetails> list = new ArrayList<ProductDetails>(); 
			while (resultSet.next()) {
				ProductDetails productDetails = new ProductDetails();
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

