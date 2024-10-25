drop database if exists oop;

create database if not exists oop;

use oop;

create table author (
    id int not null primary key ,
    name varchar(255)
);

create table book_author (
    book_id int not null,
    author_id int not null,

    primary key (book_id, author_id)
);

create table book (
    id int not null primary key ,
    title varchar(255),
    category_id int
);

create table category (
    id int not null primary key ,
    name varchar(255)
);

create table waitlist (
    customer_id int not null,
    book_id int not null,

    primary key (customer_id, book_id)
);

create table customer_account (
    card_number int not null primary key ,
    first_name varchar(255),
    surname varchar(255),
    email varchar(255),
    status varchar(10)
);

create table checkout (
    id int not null primary key ,
    start_time timestamp,
    end_time timestamp,
    book_copy_id int,
    customer_account_id int,
    is_returned boolean
);

create table book_copy (
    id int not null primary key ,
    year_published int,
    book_id int ,
    publisher_id int
);

create table publisher (
    id int not null primary key ,
    name varchar(255)
);

create table hold (
    id int not null primary key,
    start_time timestamp,
    end_time timestamp,
    book_copy_id int,
    customer_account_id int
);

create table notification (
    id int not null primary key ,
    sent_at timestamp,
    type varchar(20),
    customer_account_id int
);

alter table book_author
    add constraint book_id_fk1
    foreign key (book_id)
    references oop.book(id)
    on delete cascade
    on update cascade,

    add constraint author_id_fk
    foreign key (author_id)
    references oop.author(id)
    on delete cascade
    on update cascade;

alter table book
    add constraint category_id_fk
    foreign key (category_id)
    references oop.category(id)
    on delete cascade
    on update cascade;

alter table waitlist
    add constraint customer_id_fk
    foreign key (customer_id)
    references oop.customer_account(card_number)
    on delete cascade
    on update cascade,

    add constraint book_id_fk2
    foreign key (book_id)
    references oop.book(id)
    on delete cascade
    on update cascade;

alter table checkout
    add constraint book_copy_id_fk1
    foreign key (book_copy_id)
    references oop.book_copy(id)
    on delete cascade
    on update cascade,

    add constraint customer_account_id_fk
    foreign key (customer_account_id)
    references oop.customer_account(card_number)
    on delete cascade
    on update cascade;

alter table hold
    add constraint book_copy_id_fk2
    foreign key (book_copy_id)
    references oop.book_copy(id)
    on delete cascade
    on update cascade,

    add constraint customer_account_id_fk1
    foreign key (customer_account_id)
    references oop.customer_account(card_number)
    on delete cascade
    on update cascade;

alter table notification
    add constraint customer_account_id_fk2
    foreign key (customer_account_id)
    references oop.customer_account(card_number)
    on delete cascade
    on update cascade;

alter table book_copy
    add constraint book_id_fk3
    foreign key (book_id)
    references oop.book(id)
    on delete cascade
    on update cascade ,

    add constraint publisher_id_fk
    foreign key (publisher_id)
    references oop.publisher(id)
    on delete cascade
    on update cascade ;