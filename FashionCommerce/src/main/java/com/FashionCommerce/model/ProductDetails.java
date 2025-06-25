package com.FashionCommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetails {
//id, name, brand, price, discount, category, quantity
	private int id;
	private String name;
	private String brand;
	private double price;
	private double discount;
	private String category;
	private int quantity;
	private String imageaddress;
}

