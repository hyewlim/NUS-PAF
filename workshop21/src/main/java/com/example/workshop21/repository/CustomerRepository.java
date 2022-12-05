package com.example.workshop21.repository;

import com.example.workshop21.models.Customer;
import com.example.workshop21.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import static com.example.workshop21.repository.Queries.*;

import java.util.LinkedList;
import java.util.List;

@Repository
public class CustomerRepository {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers(final int limit, final int offset) {

        //perform the query
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_ALL_CUSTOMERS, limit, offset);
        List<Customer> customers = new LinkedList<>();

        while (rs.next()) {
            customers.add(Customer.create(rs));
        }

        return customers;

    }

    public Customer getCustomer(int id) {

        CustomerRowMapper customerRowMapper = new CustomerRowMapper();

        //queryforobject throws an emptyresultdataaccessexception when result not found, below line returns an Integer or NULL if there are no rows
        return DataAccessUtils.singleResult(jdbcTemplate.query(SQL_FIND_CUSTOMER, customerRowMapper, id));
    }

    public List<Order> getCustOrder(int id) {

        //perform the query
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_CUST_ORDER, id);
        List<Order> orders = new LinkedList<>();

        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>" + rs);

        while (rs.next()) {
            orders.add(Order.create(rs));
        }


        return orders;

    }


}
