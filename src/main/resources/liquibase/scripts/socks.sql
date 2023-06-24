--liquibase formatted sql

--changeset Nikolay:1
create database warehouse;

--changeset Nikolay:2
CREATE TABLE socks
(
    color          VARCHAR(50),
    cotton_part    INTEGER,
    total_quantity INTEGER
);
