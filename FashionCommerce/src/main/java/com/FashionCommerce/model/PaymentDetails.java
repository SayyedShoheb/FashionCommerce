package com.FashionCommerce.model;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentDetails {
// paymentId, amount, paymentDate, paymentTime, paymentType, customerId, productId
	private int paymentId;
	private double amount;
	private Date paymentDate;
	private Time paymentTime;
	private String paymentType;
	private int customerId;
	private int productId;
	private int quantity;
	
}

