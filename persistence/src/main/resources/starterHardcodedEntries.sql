-- Hardcoded Entries For Schema

-- Hardcoded Entries For Rooms (room types as follow: SIMPLE, DOUBLE, TRIPLE)
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 102);
insert into room (id, room_type, is_free_now, number) values (default, 'TRIPLE', true, 103);
insert into room (id, room_type, is_free_now, number) values (default, 'SIMPLE', true, 104);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 105);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 106);

-- Hardcoded Entries For Users
insert into users (id, email, password, username, role)
values (default,'mail@mail.com', '$2a$10$NQOVaWomIVaNS4Px63eWneFOxupi4P0/MSe0KEfRaAcazw8vvoc3K', 'manager', 'MANAGER');
insert into users (id, email, password, username, role)
values (default,'mail@mail.com', '$2a$10$NQOVaWomIVaNS4Px63eWneFOxupi4P0/MSe0KEfRaAcazw8vvoc3K', 'employee', 'EMPLOYEE');
