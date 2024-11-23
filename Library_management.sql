drop database if exists library_management;

create database if not exists library_management;

use library_management;

create table members(
    member_id int not null auto_increment,
    firstName varchar(45),
    lastName varchar(45),
    gender varchar(10),
    birth date,
    email varchar(100),
    phone varchar(15),
    primary key (member_id)
);

create table admins (
    admin_id int not null auto_increment,
    admin_name varchar(45) not null unique ,
    firstName varchar(45),
    lastName varchar(45),
    password varchar(45),
    gender varchar(10),
    birth date,
    email varchar(100),
    phone varchar(15),
    primary key (admin_id)
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
    id int not null auto_increment,
    member_id int not null,
    book_id int  not null ,
    book_name varchar(45),
    date_out datetime,
    due_date datetime,
    status varchar(45),
    primary key (id)
);

create table payment (
    id int not null auto_increment,
    member_id int not null ,
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
        foreign key (member_id)
        references members (member_id)
        on update cascade
        on delete no action;

alter table payment
    add constraint fk_userID_2
        foreign key (member_id)
        references members (member_id)
        on update cascade
        on delete no action;

INSERT INTO members (firstName, lastName, gender, birth, email, phone)
VALUES
('John', 'Doe', 'Male', '1990-01-15 00:00:00', 'john.doe@example.com', '0123456789'),
('Jane', 'Smith', 'Female', '1995-07-20 00:00:00', 'jane.smith@example.com', '0987654321'),
('Alice', 'Brown', 'Female', '1988-11-05 00:00:00', 'alice.brown@example.com', '0123456788'),
('Bob', 'Jones', 'Male', '1992-03-30 00:00:00', 'bob.jones@example.com', '0123456799'),
('Charlie', 'Clark', 'Male', '1998-12-10 00:00:00', 'charlie.clark@example.com', '0987654322'),
('Emily', 'Davis', 'Female', '1993-04-25 00:00:00', 'emily.davis@example.com', '0123456787'),
('Frank', 'Moore', 'Male', '1985-09-18 00:00:00', 'frank.moore@example.com', '0987654323'),
('Grace', 'Hill', 'Female', '1991-06-12 00:00:00', 'grace.hill@example.com', '0123456798'),
('Henry', 'Wilson', 'Male', '1987-10-28 00:00:00', 'henry.wilson@example.com', '0987654324'),
('Isabel', 'Martin', 'Female', '1999-02-14 00:00:00', 'isabel.martin@example.com', '0123456786');

INSERT INTO books (book_id, book_name, book_author, price, time_of_borrow, quantity_in_store, borrowing, remaining_quantity)
VALUES
('B001', 'To Kill a Mockingbird', 'Harper Lee', 150.0, 14, 50, 5, 45),
('B002', '1984', 'George Orwell', 120.0, 14, 40, 8, 32),
('B003', 'Pride and Prejudice', 'Jane Austen', 100.0, 14, 30, 3, 27),
('B004', 'The Great Gatsby', 'F. Scott Fitzgerald', 130.0, 14, 60, 10, 50),
('B005', 'Moby Dick', 'Herman Melville', 170.0, 21, 25, 7, 18),
('B006', 'War and Peace', 'Leo Tolstoy', 200.0, 21, 20, 2, 18),
('B007', 'The Odyssey', 'Homer', 180.0, 21, 35, 5, 30),
('B008', 'The Catcher in the Rye', 'J.D. Salinger', 140.0, 14, 45, 4, 41),
('B009', 'Animal Farm', 'George Orwell', 110.0, 14, 50, 6, 44),
('B010', 'The Lord of the Rings', 'J.R.R. Tolkien', 300.0, 30, 20, 10, 10),
('B011', 'Harry Potter and the Philosopher\'s Stone', 'J.K. Rowling', 250.0, 14, 100, 20, 80),
('B012', 'The Alchemist', 'Paulo Coelho', 190.0, 14, 70, 15, 55),
('B013', 'Don Quixote', 'Miguel de Cervantes', 220.0, 21, 15, 3, 12),
('B014', 'Jane Eyre', 'Charlotte Brontë', 150.0, 14, 40, 8, 32),
('B015', 'The Book Thief', 'Markus Zusak', 170.0, 14, 60, 10, 50),
('B016', 'The Hobbit', 'J.R.R. Tolkien', 190.0, 14, 30, 12, 18),
('B017', 'Fahrenheit 451', 'Ray Bradbury', 120.0, 14, 25, 4, 21),
('B018', 'Brave New World', 'Aldous Huxley', 150.0, 14, 35, 7, 28),
('B019', 'The Kite Runner', 'Khaled Hosseini', 180.0, 14, 50, 15, 35),
('B020', 'The Hunger Games', 'Suzanne Collins', 200.0, 14, 70, 20, 50);