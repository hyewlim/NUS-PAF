package com.example.workshop22.repositories;

public class Queries {

    public static final String SQL_SELECT_ALL_RSVP =
            "SELECT * FROM RSVP";

    public static final String SQL_FIND_A_RSVP =
            "SELECT * FROM RSVP WHERE NAME LIKE ?";

    public static final String SQL_ADD_A_RSVP =
            "INSERT INTO RSVP (name, email, phone, confirmation_date, comments) VALUES (?, ?, ?, ?, ?)";

    public static final String SQL_UPDATE_EMAIL =
            "update rsvp set name = ?, phone = ? , confirmation_date = ?, comments = ? where email = ?";

    public static final String SQL_SELECT_COUNT =
            "SELECT COUNT(EMAIL) as total FROM RSVP";
}
