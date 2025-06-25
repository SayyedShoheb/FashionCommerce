package com.FashionCommerce.service;

import com.FashionCommerce.model.CustomerDetails;

public interface CustomerService {
boolean userRegistration(CustomerDetails customerDetails);
boolean customerLogin(String emailId,String password);
}

