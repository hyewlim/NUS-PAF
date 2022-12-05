package com.example.workshop23.controllers;

import com.example.workshop23.models.Order;
import com.example.workshop23.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/order")
public class OrderController {

    final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService service;

    @GetMapping
    public String getTotal(@PathVariable("id") int orderId, Model model) {

        System.out.println(orderId);
        Order order = service.getOrderTotal(orderId);
        model.addAttribute("order", order);
        logger.info(">>>>>>>>>>>>>>>>>" + order.toString());
        return "index";
    }

}
