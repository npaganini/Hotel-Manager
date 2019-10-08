-- Hardcoded Entries For Schema

-- Hardcoded Entries For Rooms (room types as follow: SIMPLE, DOUBLE, TRIPLE)
insert into room (id, room_type, is_free_now, number) values (1, 'DOUBLE', true, 102);
insert into room (id, room_type, is_free_now, number) values (2, 'TRIPLE', true, 103);
insert into room (id, room_type, is_free_now, number) values (3, 'SIMPLE', true, 104);
insert into room (id, room_type, is_free_now, number) values (4, 'DOUBLE', true, 105);
insert into room (id, room_type, is_free_now, number) values (5, 'DOUBLE', true, 106);

-- Hardcoded Entries For Products
insert into product (id, description, price, file_path, enable) values (1, 'Coca-Cola', 45.3, 'filePath', true);
insert into product (id, description, price, file_path, enable) values (2, 'Whisky', 150, 'filePath', true);
insert into product (id, description, price, file_path, enable) values (3, 'Papitas', 12, 'filePath', true);
insert into product (id, description, price, file_path, enable) values (4, 'Chocolates', 19, 'filePath', true);

-- Hardcoded Entries For Users
insert into users (id, email, password, username, role)
values (1, 'mail@mail.com', '$2a$10$NQOVaWomIVaNS4Px63eWneFOxupi4P0/MSe0KEfRaAcazw8vvoc3K', 'manager', 'MANAGER');
insert into users (id, email, password, username, role)
values (2, 'mail@mail.com', '$2a$10$NQOVaWomIVaNS4Px63eWneFOxupi4P0/MSe0KEfRaAcazw8vvoc3K', 'employee', 'EMPLOYEE');
