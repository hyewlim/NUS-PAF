package com.example.workshop21.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class Order {

    private DateTime orderDate;
    private int orderId;
    private int productId;
    private double quantity;
    private double unitPrice;

    public DateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public static Order create(SqlRowSet rs) {
        Order order = new Order();
        order.setOrderDate(new DateTime(DateTimeFormat.forPattern("MM/dd/yyyy").parseDateTime(rs.getString("order_date"))));
//        order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
        order.setOrderId(rs.getInt("order_id"));
        order.setProductId(rs.getInt("product_id"));
        order.setQuantity(rs.getDouble("quantity"));
        order.setUnitPrice(rs.getDouble("unit_price"));

        return order;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("orderId", getOrderId())
//                .add("orderDate", String.valueOf(getOrderDate()))
                .add("productId", getProductId())
                .add("quantity", getQuantity())
                .add("unitPrice", getUnitPrice())
                .build();
    }
}
