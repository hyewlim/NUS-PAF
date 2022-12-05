package com.example.workshop23.controllers;

import com.example.workshop23.models.Order;
import com.example.workshop23.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/order/total", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRestController {

    @Autowired
    private OrderService service;

    @GetMapping(path="{orderId}")
    public ResponseEntity<String> getOrderDetails(@PathVariable int orderId) {
        System.out.println(orderId);
        Order order = service.getOrderTotal(orderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(order.toJson().toString());
    }
}
