drop database if exists library_management;

create database if not exists library_management;

use library_management;

create table members(
    userID int not null auto_increment,
    username varchar(45) not null unique ,
    firstName varchar(45),
    lastName varchar(45),
    sex varchar(10),
    birth datetime,
    email varchar(100),
    phone varchar(15),
    primary key (userID)
);

create table admins (
    admin_ID int not null auto_increment,
    admin_name varchar(45) not null unique ,
    firstName varchar(45),
    lastName varchar(45),
    password varchar(45),
    sex varchar(10),
    birth datetime,
    email varchar(100),
    phone varchar(15),
    primary key (admin_ID)
);

create table books (
    book_id varchar(10) not null ,
    book_name varchar(45),
    book_author varchar(45),
    price double,
    time_of_borrow int,
    quantity_in_store int,
    borrowing int,
    remaining_quantity int,
    primary key (book_id)
);

create table borrow (
    ID int not null auto_increment,
    userID int not null,
    username varchar(45),
    book_id int  not null ,
    book_name varchar(45),
    date_out datetime,
    due_date datetime,
    status varchar(45),
    primary key (ID)
);

create table payment (
    ID int not null auto_increment,
    userID int not null ,
    username varchar(45),
    book_id int not null ,
    book_name varchar(45),
    order_date datetime,
    quantity_of_order int not null,
    price double,
    payment double,
    primary key (ID)
);

alter table borrow
    add constraint fk_userID_1
        foreign key (userID)
        references members (userID)
        on update cascade
        on delete no action;

alter table payment
    add constraint fk_userID_2
        foreign key (userID)
        references members (userID)
        on update cascade
        on delete no action;