create schema medikapp;
use medikapp;
create table user (
    id int primary key,
    name varchar(30),
    mobile_no varchar(10),
    password varchar(20),
    email varchar(35)
);
select*from user;
alter table user modify column id int auto_increment;

create table pharmacy_admin (
    product_id int primary key,
    product_name varchar(100),
    product_image longblob,
    product_quantity int,
    product_price int,
    description varchar(300),
    uses varchar(200),
    contains varchar(200),
    product_category varchar(20),mfd_date date,exp_date date,is_deleted int default 0
);
select*from pharmacy_admin;