package com.example.workshop21.repository;

public class Queries {

    public static final String SQL_SELECT_ALL_CUSTOMERS =
            "select id, first_name, last_name, job_title from Customers limit ? offset ?";

    public static final String SQL_FIND_CUSTOMER =
            "select id, first_name, last_name, job_title from Customers where id=?";

    public static final String SQL_GET_CUST_ORDER =
            "select customers.id, first_name, last_name, order_date, order_id, product_id, quantity, unit_price from customers left join orders on customers.id=orders.customer_id left join order_details on orders.id=order_details.order_id where customers.id=?";
}
