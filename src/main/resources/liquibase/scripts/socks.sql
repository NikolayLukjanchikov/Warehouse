--liquibase formatted sql

--changeset Nikolay:1
CREATE TABLE socks
(
    id      BIGINT,
    color   VARCHAR(50),
    cotton_part  VARCHAR(50)
);
