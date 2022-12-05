package com.example.workshop23.service;

import com.example.workshop23.models.Order;
import com.example.workshop23.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order getOrderTotal(int orderId) {
        return orderRepository.getOrderTotal(orderId);
    }

}
