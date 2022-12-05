package com.example.workshop23.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {

    private int orderId;
    private LocalDate orderDate;
    private int custId;
    private double totalPriceOfOrder;
    private double totalCost;



    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public double getTotalPriceOfOrder() {
        return totalPriceOfOrder;
    }

    public void setTotalPriceOfOrder(double totalPriceOfOrder) {
        this.totalPriceOfOrder = totalPriceOfOrder;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public static Order create(SqlRowSet rs) {

        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setOrderDate(rs.getDate("order_date").toLocalDate());
        order.setCustId(rs.getInt("customer_id"));
        order.setTotalPriceOfOrder(rs.getDouble("total_price_of_order"));
        order.setTotalCost(rs.getDouble("total_cost"));

        return order;

    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("orderId", getOrderId())
                .add("orderDate", String.valueOf(getOrderDate()))
                .add("customerId", getCustId())
                .add("totalPrice", getTotalPriceOfOrder())
                .add("totalCOst", getTotalCost())
                .build();




    }
}
