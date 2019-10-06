-- Schema Table Creation

-- Products Table Creation (name = product)
CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    description VARCHAR(150),
    price DOUBLE PRECISION,
    file_path VARCHAR(150),
    enable boolean
);

-- Users Table Creation (name = users)
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    email varchar(100),
    username varchar(100),
    password varchar(100),
    role varchar(15)
);

-- Rooms Table Creation (name = room)
CREATE TABLE IF NOT EXISTS room (
    id SERIAL PRIMARY KEY,
    room_type VARCHAR(15),
    is_free_now BOOLEAN,
    number INTEGER
);

-- Reservations Table Creation (name = reservation)
CREATE TABLE IF NOT EXISTS reservation (
    id SERIAL PRIMARY KEY,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    user_email VARCHAR(100),
    hash VARCHAR(1000),
    is_active BOOLEAN,
    room_id INTEGER REFERENCES room(id),
    user_id INTEGER REFERENCES users(id)
);

-- Charges Table Creation (name = charge)
CREATE TABLE IF NOT EXISTS charge (
    id SERIAL PRIMARY KEY,
    product_id INTEGER REFERENCES product(id),
    reservation_id INTEGER REFERENCES reservation(id),
    delivered BOOLEAN
);



-- Hardcoded starter data for non-empty database

-- Starter Rooms Creation
insert into room (id, room_type, is_free_now, number) values (1, 'DOUBLE', true, 102);
insert into room (id, room_type, is_free_now, number) values (2, 'TRIPLE', true, 103);
insert into room (id, room_type, is_free_now, number) values (3, 'SIMPLE', true, 104);
insert into room (id, room_type, is_free_now, number) values (4, 'DOUBLE', true, 105);
insert into room (id, room_type, is_free_now, number) values (5, 'DOUBLE', true, 106);

-- Starter Products Creation
insert into product (id, description, price, file_path, enable) VALUES (1, 'Coca-Cola', 45.3, 'filePath', true);
insert into product (id, description, price, file_path, enable) VALUES (2, 'Whisky', 150, 'filePath', true);
insert into product (id, description, price, file_path, enable) VALUES (3, 'Papitas', 12, 'filePath', true);
insert into product (id, description, price, file_path, enable) VALUES (4, 'Chocolates', 19, 'filePath', true);

