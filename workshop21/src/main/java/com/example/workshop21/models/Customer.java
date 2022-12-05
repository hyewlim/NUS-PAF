package com.example.workshop21.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Customer {

    private int id;
    private String firstName;
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static Customer create(SqlRowSet rs) {
        Customer customer = new Customer();
        customer.setId((rs.getInt("id")));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));

        return customer;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", getId())
                .add("firstName", getFirstName())
                .add("lastName", getLastName())
                .build();
    }
}
