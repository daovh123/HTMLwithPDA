drop database library_management;

create database if not exists library_management;

use library_management;

create table book(
    book_id int not null auto_increment,
    book_name varchar(255),
    author varchar(255),
    published_date timestamp,
    price double,
    view int,
    primary key (book_id)
);

create table users(
    userID int not null auto_increment,
    username varchar(255) unique ,
    firstName varchar(255),
    lastName varchar(255),
    password varchar(255),
    email varchar(255),
    phoneNum varchar(15),
    primary key (userID)
);

create table borrow (
    userID int not null,
    username varchar(255),
    book_id int  not null ,
    book_name varchar(255),
    date_out datetime,
    due_date datetime,
    primary key (userID, book_id)
);

create table payment (
    userID int not null ,
    username varchar(255),
    book_id int not null ,
    book_name varchar(255),
    order_date datetime,
    quantity_of_order int not null,
    price double,
    payment double,
    primary key (userID, book_id)
);

alter table borrow
    add constraint fk_userID_1
        foreign key (userID)
        references users (userID)
        on update cascade
        on delete no action,
    add constraint fk_book_id_1
        foreign key (book_id)
        references book (book_id)
        on update cascade
        on delete no action ;

alter table payment
    add constraint fk_userID_2
        foreign key (userID)
        references users (userID)
        on update cascade
        on delete no action,
    add constraint fk_book_id_2
        foreign key (book_id)
        references book (book_id)
        on update cascade
        on delete no action ;

insert into users(username, firstName, lastName, password, email, phoneNum)
    values ('huyanh', 'Le', 'Huy Anh', '23021463', '23021464@vnu.edu.vn', '0866502235');

insert into users(username, firstName, lastName, password, email, phoneNum)
    values ('Fanny', 'Zhuxin', 'Kagura', '23021464', 'lehuyanh423015@gmail.com', '0866502235');

insert into users (username, firstName, lastName, password, email, phoneNum)
    values ('Lylia', 'Lolita', 'Zhark', '23021464', 'lehuyanhk60@gmail.com', '0866502235');
