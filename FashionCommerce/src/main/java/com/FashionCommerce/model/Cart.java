package com.FashionCommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart {
//	cartId, customerId, productId
	private int cardId;
	private int customerId;
	private int productId;
}

