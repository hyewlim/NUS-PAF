package com.example.workshop23.repositories;

public class Queries {

    static final String SQL_GET_TOTAL_PRICE=
            """
                SELECT
                    order_details.order_id,
                    DATE_FORMAT(orders.order_date, \"%d/%m/%Y\") as order_date,
                    orders.customer_id,
                    sum(order_details.unit_price * order_details.quantity) as total_price_of_order,
                    sum(order_details.quantity * products.standard_cost) as total_cost
                FROM
                    orders,
                    order_details,
                    products
                WHERE
                    orders.id=order_details.order_id
                    AND order_details.product_id=products.id
                    AND orders.id=?        
            """;
}
