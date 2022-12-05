create table rsvp (
    name varchar(128) not null,
    email varchar(128) not null,
    phone varchar(128) not null,
    confirmation_date date not null,
    comments text not null,

    primary key(email)
)

INSERT INTO rsvp (name, email, phone, confirmation_date, comments)
VALUES ('Hong', 'hong@gmail.com', 344829348, '2022-11-22');