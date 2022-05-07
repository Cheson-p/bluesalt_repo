create sequence hibernate_sequence start 1 increment 1;

DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
 "id" VARCHAR(255) NOT NULL,
 "balance" VARCHAR(255) DEFAULT NULL,
 "firstname" VARCHAR(255) DEFAULT NULL,
 "lastname" VARCHAR(50) DEFAULT NULL,
  primary key (id)
)