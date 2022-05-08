CREATE TABLE IF NOT EXISTS stats
(
    id   INTEGER PRIMARY KEY ,
    capital  VARCHAR(200) NOT NULL ,
    name_country VARCHAR(254) NOT NULL
    );

CREATE SEQUENCE country_id_seq START WITH 10 INCREMENT BY 1;