package com.artshop.service;

import com.artshop.model.Order;
import com.artshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }
    
    public List<Order> getOrdersByEmail(String email) {
        return orderRepository.findByEmail(email);
    }
    
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }
    
    public Order createOrder(Order order) {
        order.setCreatedAt(System.currentTimeMillis());
        order.setStatus("PENDING");
        // Mask credit card number for security
        if (order.getCardNumber() != null && order.getCardNumber().length() > 4) {
            String maskedCard = "**** **** **** " + order.getCardNumber().substring(order.getCardNumber().length() - 4);
            order.setCardNumber(maskedCard);
        }
        return orderRepository.save(order);
    }
    
    public Order updateOrder(String id, Order order) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            order.setId(id);
            return orderRepository.save(order);
        }
        return null;
    }
    
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
    
}
