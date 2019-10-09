-- Creación de tablas para testing

-- Products Table Creation (name = product)
CREATE TABLE IF NOT EXISTS product
(
    id          IDENTITY PRIMARY KEY,
    description VARCHAR(150),
    price       DOUBLE PRECISION,
    file        VARBINARY(1000000),
    enable      boolean
);

-- Users Table Creation (name = users)
CREATE TABLE IF NOT EXISTS users
(
    id       IDENTITY PRIMARY KEY,
    email    varchar(100),
    username varchar(100),
    password varchar(100),
    role     varchar(15)
);

-- Rooms Table Creation (name = room)
CREATE TABLE IF NOT EXISTS room
(
    id          IDENTITY PRIMARY KEY,
    room_type   VARCHAR(15),
    is_free_now BOOLEAN,
    number      INTEGER
);

-- Reservations Table Creation (name = reservation)
CREATE TABLE IF NOT EXISTS reservation
(
    id         IDENTITY PRIMARY KEY,
    start_date time,
    end_date   time,
    user_email VARCHAR(100),
    hash       VARCHAR(1000),
    is_active  BOOLEAN,
    room_id    INTEGER REFERENCES room (id),
    user_id    INTEGER REFERENCES users (id)
);

-- Charges Table Creation (name = charge)
CREATE TABLE IF NOT EXISTS charge
(
    id             IDENTITY PRIMARY KEY,
    product_id     INTEGER REFERENCES product (id),
    reservation_id INTEGER REFERENCES reservation (id),
    delivered      BOOLEAN
);


