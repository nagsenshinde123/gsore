package com.gsore.service;

import java.util.List;

import com.gsore.domain.Order;
import com.gsore.domain.Payment;
import com.gsore.domain.Shipping;
import com.gsore.domain.ShoppingCart;
import com.gsore.domain.User;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);
	
	List<Order> findByUser(User user);
	
	Order findOrderWithDetails(Long id);
}
