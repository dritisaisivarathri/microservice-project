package com.bookstore.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.orderservice.client.ProductClient;
import com.bookstore.orderservice.dto.ProductDTO;
import com.bookstore.orderservice.model.Order;
import com.bookstore.orderservice.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;

	@Autowired
	private ProductClient productClient;
    
	@CircuitBreaker(name="productService",fallbackMethod="createOrderFallBack")
	public Order createOrder(Order order){

		ProductDTO product = productClient.getProduct(order.getProductId());

		if(product==null){
			throw new RuntimeException("Product not found");
		}

		order.setProductDescription(product.getProductDescription());
		order.setProductPrice(product.getProductPrice());

		return repo.save(order);
	}
	
	public String createOrderFallBack(Order request, Throwable ex) {
		return "Product service not active"; 
		
	}
}