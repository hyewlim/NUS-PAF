package com.example.workshop21.controller;

import com.example.workshop21.models.Customer;
import com.example.workshop21.models.Order;
import com.example.workshop21.repository.CustomerRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.json.JsonArrayBuilder;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api", produces= MediaType.APPLICATION_JSON_VALUE)
public class RESTcontroller {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public ResponseEntity<String> getCustomers(@RequestParam(defaultValue = "5") int limit,
                                               @RequestParam(defaultValue = "0") int offset){

        //Query the database for customers
        List<Customer> customerList = customerRepository.getAllCustomers(limit, offset);

        //Build the result
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Customer c: customerList)
            arrayBuilder.add(c.toJson());
        JsonArray result = arrayBuilder.build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable int id){

        Optional<Customer> customer = Optional.ofNullable(customerRepository.getCustomer(id));

        if (customer.isEmpty()) {
            JsonObject error = Json.createObjectBuilder()
                    .add("error", "Cannot find Customer %s".formatted(id)).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());

        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(customer);
        }

    }

    @GetMapping("/customer/{id}/orders")
    public ResponseEntity<?> getCustomerOrder(@PathVariable int id) {

        //get the list of orders
        List<Order> orders = customerRepository.getCustOrder(id);

        //build an array
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Order o : orders)
            arrayBuilder.add(o.toJson());
        JsonArray result = arrayBuilder.build();

        if (orders.get(0).getOrderId() == 0) {
            JsonObject err = Json.createObjectBuilder()
                    .add("error", "Cannot find order for Customer %s".formatted(id)).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.toString());
        } else {

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result.toString());
        }
    }
}
