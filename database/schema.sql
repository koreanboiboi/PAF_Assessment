--STEP 1 Create a database called eshop
--STEP 2 create a table called customers in the eshop database
-- STEP 3 SQL insert statement

-- drop database if exists eshop;

-- create database eshop;

-- use eshop;

CREATE TABLE customers
(
    name varchar(32) not null,
    address varchar(128) not null,
    email varchar(128) not null,
    primary key(name)
);

-- LOAD DATA INFILE 'C:\Users\yungi\VTTP\PAF\EXAM\eshop\database\data.csv'
-- INTO TABLE customers 
-- FIELDS TERMINATED BY ',' 
-- LINES TERMINATED BY '\n'
-- IGNORE 1 ROWS;

create table line_item (
	order_id char (8) not null,
    name varchar(32) not null,
    quantity int,
    item varchar(128) not null,
    primary key(order_id)
  );


SELECT line_item.order_id, customers.name, customers.address, customers.email, line_item.item, line_item.quantity
FROM customers
INNER JOIN line_item ON customers.name=line_item.name;

create table order_status (
	order_id char (8) not null,
    delivery_id varchar (128),
	status SET ("pending","dispatched"),
    satus_update datetime
  );