-- liquibase formatted sql

-- changeset _:1
create table sample_entity
(
    id   smallserial not null,
    code varchar(255),
    name varchar(255),
    primary key (id)
);
-- rollback

-- changeset _:2
INSERT INTO sample_entity (id, code, name)
VALUES (1, 'code', 'name'),
       (2, 'code', 'name'),
       (3, 'code', 'name');

/* хз почему, но serival id ломается при дропе бд*/
SELECT setval('sample_entity_id_seq', 3);
-- rollback