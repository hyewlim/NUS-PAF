package com.example.workshop22.model;

import com.example.workshop22.model.RSVP;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RSVPRowMapper implements RowMapper<RSVP> {


    @Override
    public RSVP mapRow(ResultSet rs, int rowNum) throws SQLException {
        RSVP rsvp = new RSVP();
        rsvp.setName(rs.getString("name"));
        rsvp.setEmail(rs.getString("email"));
        rsvp.setPhone(rs.getString("phone"));
        rsvp.setDate(rs.getDate("confirmation_date"));
        rsvp.setText(rs.getString("comments"));
        return rsvp;
    }
}
