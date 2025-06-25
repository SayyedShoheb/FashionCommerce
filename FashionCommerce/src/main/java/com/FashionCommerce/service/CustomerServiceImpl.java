package com.FashionCommerce.service;



import java.util.List;

import com.FashionCommerce.DAO.CustomerDAO;
import com.FashionCommerce.DAO.CustomerDAOImpl;
import com.FashionCommerce.exception.CustomerException;
import com.FashionCommerce.model.CustomerDetails;

public class CustomerServiceImpl implements CustomerService {
	CustomerDAO customerDAO=new CustomerDAOImpl();
	List<CustomerDetails> allCustomerDetails;
	@Override
	public boolean userRegistration(CustomerDetails customerDetails) {
		
		allCustomerDetails=customerDAO.getAllCustomerDetails();
		boolean emailMatch=allCustomerDetails.stream()
				.anyMatch((customer ->customer.getEmailId().equalsIgnoreCase(customerDetails.getEmailId())));
		if(emailMatch) {
			throw new CustomerException("Already Email Exist");
		}
		boolean mobileNumberMatch=allCustomerDetails.stream().anyMatch((customer ->customer.getMobileNumber()==(customerDetails.getMobileNumber())));
		if(mobileNumberMatch) {
			throw new CustomerException("Already Mobile Number Exist");
		}
		boolean passwordMatch=allCustomerDetails.stream().anyMatch((customer ->customer.getPassword().equals(customerDetails.getPassword())));
		if(passwordMatch) {
			throw new CustomerException("Already Password Exist");
		}
		if(customerDAO.insertCustomerDetails(customerDetails)!=0) {
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public boolean customerLogin(String emailId, String password) {
		allCustomerDetails=customerDAO.getAllCustomerDetails();
		boolean emailMatch=allCustomerDetails.stream().anyMatch((email ->email.getEmailId().equalsIgnoreCase(emailId)));
		boolean passwordMatch=allCustomerDetails.stream().anyMatch((passw ->passw.getPassword().equalsIgnoreCase(password)));
		if(emailMatch && passwordMatch) {
			return true;
		}
		else {
			return false;
		}
	}

}

