package com.FashionCommerce.DAO;



import java.util.List;

import com.FashionCommerce.model.CustomerDetails;

public interface CustomerDAO {
	int insertCustomerDetails(CustomerDetails customerDetails);
	List <CustomerDetails> getAllCustomerDetails();
	CustomerDetails getLoginCustomerDetails(String emailId,String password);
}

