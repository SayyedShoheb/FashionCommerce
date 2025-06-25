package com.FashionCommerce.model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDetails {
	//id, name, emailId, mobileNumber, gender, address, password
	private int id;
	private String name;
	private String emailId;
	private long mobileNumber;
	private String gender;
	private String address;
	private String password;	
}

