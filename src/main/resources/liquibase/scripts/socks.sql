--liquibase formatted sql

--changeset Nikolay:1
CREATE TABLE socks
(
    color          VARCHAR(50),
    cotton_part    INTEGER,
    total_quantity INTEGER
);
