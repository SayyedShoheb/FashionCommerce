package com.FashionCommerce.service;


import java.util.List;
import java.util.Optional;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;

import com.FashionCommerce.DAO.AdminDAO;
import com.FashionCommerce.DAO.AdminDAOImpl;
import com.FashionCommerce.model.AdminDetails;
import com.FashionCommerce.model.ProductDetails;

public class AdminServiceImpl implements AdminService{
	AdminDAO adminDAO=new AdminDAOImpl();
	@Override
	public boolean adminLogin(String emailId, String password) {
		
		List<AdminDetails> list=adminDAO.getAllAdminDetails();
		
		boolean result=list.stream().anyMatch((admin->admin.getEmailId().equals(emailId) & admin.getPassword().equals(password)));
		if(result) {
			return true;
		}
		else {
			return false;
		}
	}

}
