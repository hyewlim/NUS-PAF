package com.example.workshop22.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.io.ByteArrayInputStream;
import java.sql.Date;

public class RSVP {

    private String name;
    private String email;
    private String phone;
    private Date date;
    private String text;

    private Integer totalCount;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static RSVP create(SqlRowSet rs) {

        RSVP rsvp = new RSVP();
        rsvp.setName(rs.getString("name"));
        rsvp.setEmail(rs.getString("email"));
        rsvp.setPhone(rs.getString("phone"));
        rsvp.setDate(rs.getDate("confirmation_date"));
        rsvp.setText(rs.getString("comments"));

        return rsvp;
    }

    public static RSVP create(String json) {
        JsonReader reader = Json.createReader(new ByteArrayInputStream(json.getBytes()));
        return create(reader.readObject());

    }

//    public static RSVP updatewithEmail(String json, String email) {
//        JsonReader reader = Json.createReader(new ByteArrayInputStream(json.getBytes()));
//        RSVP rsvp = create(reader.readObject());
//        rsvp.setEmail(email);
//
//    }

    public static RSVP create(JsonObject json) {

        RSVP rsvp = new RSVP();
        rsvp.setName(json.getString("name"));
        rsvp.setEmail(json.getString("email"));
        rsvp.setPhone(String.valueOf(json.getInt("phone")));
        rsvp.setDate(Date.valueOf(json.getString("confirmation_date")));
        rsvp.setText(json.getString("comments"));

        return rsvp;
    }

    public JsonObject toJson(){
        return Json.createObjectBuilder()
                .add("name", getName())
                .add("email", getEmail())
                .add("phone", getPhone())
                .add("date", String.valueOf(getDate()))
                .add("comments", getText())
                .build();
    }



}
