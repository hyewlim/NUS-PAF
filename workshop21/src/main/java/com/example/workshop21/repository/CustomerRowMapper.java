package com.example.workshop21.repository;

import com.example.workshop21.models.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {



    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

            Customer customer = new Customer();
            customer.setId((rs.getInt("id")));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));


            return customer;

    }


}
