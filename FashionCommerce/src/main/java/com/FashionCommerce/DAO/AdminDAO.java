package com.FashionCommerce.DAO;

import java.util.List;

import com.FashionCommerce.model.AdminDetails;
import com.FashionCommerce.model.ProductDetails;

public interface AdminDAO {
	List<AdminDetails> getAllAdminDetails();
	AdminDetails verifyAdminLoginCredentials(String emailId,String password);
}
