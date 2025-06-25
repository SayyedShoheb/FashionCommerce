package com.FashionCommerce.DAO;

import java.util.List;

import com.FashionCommerce.model.Cart;
import com.FashionCommerce.model.ProductDetails;

public interface CartDAO {
	int productsCountInCart(int customerId);
	int insertProductInCart(Cart cart);
	boolean getDetailsOfTheProduct(int customerId,int productId);
	List<ProductDetails>  getAllCartProducts(int customerId);
}
