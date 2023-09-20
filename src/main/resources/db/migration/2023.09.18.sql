-- liquibase formatted sql

-- changeset _:1
create table sample_entity
(
    id   serial not null,
    code varchar(255),
    name varchar(255),
    primary key (id)
);
-- rollback