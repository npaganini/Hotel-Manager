-- Hardcoded Entries For Schema

-- Hardcoded Entries For Rooms (room types as follow: SIMPLE, DOUBLE, TRIPLE)
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 102);
insert into room (id, room_type, is_free_now, number) values (default, 'TRIPLE', true, 103);
insert into room (id, room_type, is_free_now, number) values (default, 'SIMPLE', true, 104);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 105);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 106);

insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 202);
insert into room (id, room_type, is_free_now, number) values (default, 'TRIPLE', true, 203);
insert into room (id, room_type, is_free_now, number) values (default, 'SIMPLE', true, 204);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 205);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 206);

insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 302);
insert into room (id, room_type, is_free_now, number) values (default, 'TRIPLE', true, 303);
insert into room (id, room_type, is_free_now, number) values (default, 'SIMPLE', true, 304);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 305);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 306);

insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 402);
insert into room (id, room_type, is_free_now, number) values (default, 'TRIPLE', true, 403);
insert into room (id, room_type, is_free_now, number) values (default, 'SIMPLE', true, 404);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 405);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 406);

insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 502);
insert into room (id, room_type, is_free_now, number) values (default, 'TRIPLE', true, 503);
insert into room (id, room_type, is_free_now, number) values (default, 'SIMPLE', true, 504);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 505);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 506);

insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 602);
insert into room (id, room_type, is_free_now, number) values (default, 'TRIPLE', true, 603);
insert into room (id, room_type, is_free_now, number) values (default, 'SIMPLE', true, 604);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 605);
insert into room (id, room_type, is_free_now, number) values (default, 'DOUBLE', true, 606);

-- Hardcoded Entries For Users
insert into users (id, email, password, username, role)
values (default,'mail@mail.com', '$2a$10$NQOVaWomIVaNS4Px63eWneFOxupi4P0/MSe0KEfRaAcazw8vvoc3K', 'manager', 'MANAGER');
insert into users (id, email, password, username, role)
values (default,'mail@mail.com', '$2a$10$NQOVaWomIVaNS4Px63eWneFOxupi4P0/MSe0KEfRaAcazw8vvoc3K', 'employee', 'EMPLOYEE');
