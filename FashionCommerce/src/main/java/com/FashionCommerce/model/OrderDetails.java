package com.FashionCommerce.model;



import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetails {
//orderId, customerId, productId, purchaseQuantity, purchaseDate, totalPurchasePrice
	private int orderId;
	private int customerId;
	private int productId;
	private int puchaseQuantity;
	private Date purchaseDate;
	private double totoalPurchasePrice;
}

