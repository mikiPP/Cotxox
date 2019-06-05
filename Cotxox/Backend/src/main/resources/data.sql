truncate fee;
truncate user;
truncate driver;
truncate fare;

insert into fee
values (1, 1.35, 'Mille Cost');
insert into fee
values (2, 0.35, 'Minute Cost');
insert into fee
values (3, 5, 'Minimum Cost');
insert into fee
values (4, 0.2, 'Commission Percentage');

insert into driver
values (1, 0, '8255 HVT', '500 Abarth', 'Érik Vila Diaz', 4.1, 1);
insert into driver
values (2, 1, '8221 LJS', 'Alfa Romeo Giulietta', 'Elena Perez Crespo', 4.35, 1);
insert into driver
values (3, 1, '5179 LXS', 'Bentley Flying Spur', 'Pablo Vidal Torres', 3.95, 1);
insert into driver
values (4, 0, '8024 NST', 'Audi A7 Sportback', 'Alma Parra Vega', 4.55, 1);
insert into driver
values (5, 0, '8024 NST', 'Seat cordoba', 'Marcos Mojon Mas', 1.55, 1);