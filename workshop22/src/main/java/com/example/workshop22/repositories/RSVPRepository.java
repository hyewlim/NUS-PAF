package com.example.workshop22.repositories;

import com.example.workshop22.model.RSVP;
import com.example.workshop22.model.RSVPRowMapper;
import com.example.workshop22.model.RSVPTotalCountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;


import java.util.LinkedList;
import java.util.List;

import static com.example.workshop22.repositories.Queries.*;

@Repository
public class RSVPRepository {

    private static final Logger logger = LoggerFactory.getLogger(RSVPRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<RSVP> getAllRSVPs() {

        //perform the query
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_ALL_RSVP);
        List<RSVP> rsvps = new LinkedList<>();

        while (rs.next())
            rsvps.add(RSVP.create(rs));

        return rsvps;
    }

    public List<RSVP> getRSVP(String name) {

        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_FIND_A_RSVP, "%%%s%%".formatted(name));
        List<RSVP> rsvps = new LinkedList<>();

        while(rs.next())
            rsvps.add(RSVP.create(rs));

        return rsvps;
    }

    public boolean insertRSVP(RSVP rsvp) {

        int added = jdbcTemplate.update(
                SQL_ADD_A_RSVP,
                rsvp.getName(),
                rsvp.getEmail(),
                rsvp.getPhone(),
                rsvp.getDate(),
                rsvp.getText());

        return added > 0;
    }

    public boolean updateRSVP(RSVP rsvp) {

        int updated = jdbcTemplate.update(SQL_UPDATE_EMAIL,
                rsvp.getName(),
                rsvp.getPhone(),
                rsvp.getDate(),
                rsvp.getText(),
                rsvp.getEmail()
        );

        return updated > 0;

    }

    public int getCount() {

        List<RSVP> rsvps = jdbcTemplate.query(SQL_SELECT_COUNT, new RSVPTotalCountMapper());

        return rsvps.get(0).getTotalCount();
    }

}
