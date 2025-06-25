package com.FashionCommerce.DAO;


import java.util.List;

import com.FashionCommerce.model.ProductDetails;

public interface ProductDAO {
	int addProduct(ProductDetails productDetail);
	int deleteProduct(int productid);
	List<ProductDetails>  getAllProductsDetails();
	List<ProductDetails>  filterAllProductDetails(String filterBy);
}

