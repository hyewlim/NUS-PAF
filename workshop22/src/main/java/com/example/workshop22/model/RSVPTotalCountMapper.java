package com.example.workshop22.model;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RSVPTotalCountMapper implements RowMapper<RSVP> {


    @Override
    public RSVP mapRow(ResultSet rs, int rowNum) throws SQLException {
        RSVP r = new RSVP();
        r.setTotalCount(rs.getInt("total"));
        return r;
    }
}
