package com.example.workshop23.repositories;

import com.example.workshop23.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import static com.example.workshop23.repositories.Queries.*;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Order getOrderTotal(int orderId) {

        final List<Order> orders = new LinkedList<>();

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_TOTAL_PRICE, orderId);

        while (rs.next())
            orders.add(Order.create(rs));

        return orders.get(0);

    }
}
