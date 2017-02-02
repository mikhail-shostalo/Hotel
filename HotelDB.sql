drop database Hotel;
create database Hotel;

use Hotel;

SET GLOBAL event_scheduler = ON;

create table Roles (
	idRole int primary key auto_increment,
    role nvarchar(20)
);

create table Users(
	login nvarchar(20) primary key,
    name nvarchar(20) not null,
    lastName nvarchar(20) not null,
    password nvarchar(50) not null,
    email nvarchar(50) not null,
    phoneNumber nvarchar(50) not null,
    idRole int not null,
    constraint fkRoleUser foreign key(idRole) references Roles(idRole) 
);

create table StatusRoom(
	idStatusRoom int primary key auto_increment,
    statusRoomRu nvarchar(20) not null,
    statusRoomEn nvarchar(20) not null
);

create table ClassRoom(
	idClass int primary key auto_increment,
    classNameRu nvarchar(20) not null,
    classNameEn nvarchar(20) not null,
    descriptionRu text not null,
    descriptionEn text not null,
    numberOfPlaces int not null,
    price double(15,2) not null
);

create table Rooms(
	idRoom int primary key auto_increment,
    idStatusRoom int not null,
    constraint fkStatusRoom foreign key(idStatusRoom)
    references StatusRoom(idStatusRoom),
    idClass int not null,
    constraint fkClassRoom foreign key(idClass)
    references ClassRoom(idClass),
    numberOfRoom int not null
);

create table StatusReserve(
	idStatusReserve int primary key auto_increment,
    StatusReserveRu nvarchar(50) not null,
    StatusReserveEn nvarchar(50) not null
);

create table Reserves(
	idReserve int primary key auto_increment,
    dateReserve date not null,
    dateArrival date not null,
    dateOfDeparture date not null,
    idRoom int not null,
    constraint fkRoomReserve foreign key(idRoom)
    references Rooms(idRoom),
    login nvarchar(20) not null,
    constraint fkUserReserve foreign key(login)
    references Users(login),
    idStatusReserve int not null,
    constraint fkStatusReserve foreign key(idStatusReserve)
    references StatusReserve(idStatusReserve),
    price double(15,2) not null
);

create table Request(
	idRequest int primary key auto_increment,
    login nvarchar(20) not null,
    constraint fkUserRequest foreign key(login)
    references Users(login),
    idClass int not null,
    constraint fkClassRequest foreign key(idClass)
    references ClassRoom(idClass),
    arrivalDate date not null,
    dateOfDeparture date not null,
    numberOfPlaces int not null
);


create table Answer(
	idAnswer int primary key auto_increment,
    idRoom int not null,
    constraint fkRoomAnswer foreign key(idRoom)
    references Rooms(idRoom),
    login nvarchar(20) not null,
    constraint fkUserAnswer foreign key(login)
    references Users(login),
    arrivalDate date not null,
    dateOfDeparture date not null,
    price double(15,2) not null
);

DELIMITER $$
create trigger room_status_update
before delete on Reserves for each row
begin
update Rooms
set Rooms.idStatusRoom = 1
where Rooms.idRoom = OLD.idRoom;
end $$
DELIMITER ;

CREATE EVENT `delete_reserve`
ON SCHEDULE EVERY 1 minute
DO
DELETE FROM Reserves 
WHERE date_add(dateReserve, interval 2 day) = curdate()
and idStatusReserve = 1;

insert into Roles (role) values ('client');
insert into Roles (role) values ('manager');

insert into StatusReserve(StatusReserveRu, StatusReserveEn)
values ('забронировано', 'booked');
insert into StatusReserve(StatusReserveRu, StatusReserveEn)
values ('оплачено', 'paid');

insert into Users(name, lastName, login, password, email, phoneNumber, idRole)
values ('Михаил', 'Шостало', 'misha', 'misha122295', 'shostalomisha@outlook.com', '0969876694', 2);


insert into StatusRoom(statusRoomRu, statusRoomEn) values ('свободный', 'free');
insert into StatusRoom(statusRoomRu, statusRoomEn) values ('забронированный', 'reserved');
insert into StatusRoom(statusRoomRu, statusRoomEn) values ('занятый', 'occupied');
insert into StatusRoom(statusRoomRu, statusRoomEn) values ('недоступен', 'inaccessible');

insert into ClassRoom(classNameRu, classNameEn, descriptionRu, descriptionEn, numberOfPlaces, price)
values ('Одноместный','Single',
'В номере: душ, туалет, туалетные принадлежности,
 полуторная кровать, кабельное ТВ, электрический чайник, 
 постельное белье и полотенца.',
 'In the room: shower, toilet, toiletries, a queen-size bed,
  cable TV, water-cooker, bed sheets and towels.',
 1, 500);
 insert into ClassRoom(classNameRu, classNameEn, descriptionRu, descriptionEn, numberOfPlaces, price)
values ('Одноместный+','Single+',
'В номере: душ, туалет, туалетные принадлежности,
 полуторная кровать, кондиционер, кабельное ТВ, 
 электрический чайник, постельное белье и полотенца.',
 'In the room: shower, toilet, toiletries, a queen-size bed,
  air conditioner, cable TV, water-cooker, bed sheets and towels.',
 1, 600);
  insert into ClassRoom(classNameRu, classNameEn, descriptionRu, descriptionEn, numberOfPlaces, price)
values ('Двухместный','Twin',
'В номере: душ, туалет, туалетные принадлежности, 
 две односпальные кровати, кабельное ТВ, электрический чайник,
 постельное белье и полотенца. Подходит для одноместного и
 двухместного размещения.',
 'Room facilities: a shower, toilet room, toilet goods, 
  two single beds, cable TV, electric kettle, bed clothes 
  and towels.
  Room is suitable for single and twin accommodations.',
 2, 620);
   insert into ClassRoom(classNameRu, classNameEn, descriptionRu, descriptionEn, numberOfPlaces, price)
values ('Двухместный+','Twin+',
'В номере: душ, туалет, туалетные принадлежности, 
 большая двухспальная кровать, холодильник, кабельное ТВ,
 электрический чайник, необходимая посуда, постельное
 белье и полотенца.',
 'Room facilities: a shower, toilet room, toilet goods, 
  big double bed, computer with unlimited access to Internet,
  fridge, cable TV, electrickettle, necessary dishes, bed
  clothes and towels. Room is suitable for single and twin
  accommodations.',
 2, 800);
insert into ClassRoom(classNameRu, classNameEn, descriptionRu, descriptionEn, numberOfPlaces, price)
values ('Трехместный','Triple',
 'В номере: кабельное ТВ   электрический чайник с чашками
  постельное белье и полотенца. ванная комната и туалетные
  принадлежности.',
 'Room facilities: a shower, toilet room, toilet goods,
  three single beds, cable TV, computer with unlimited
  access to Internet, electrickettle, bed clothes and 
  towels. Room is suitable for comfortable accommodation.',
 3, 750);
insert into ClassRoom(classNameRu, classNameEn, descriptionRu, descriptionEn, numberOfPlaces, price)
values ('Трехместный+','Triple+',
 'Двухкомнатный номер под трёхместное размещение, удобства
  одни на две комнаты. В каждой комнате: кабельное ТВ, 
  электрический чайник, постельное белье и полотенца. 
  Подходит для семейного размещения.',
 'This two bedrooms accomodation is suitable for three person.
  With shared bathroom.',
 3, 1000);
insert into ClassRoom(classNameRu, classNameEn, descriptionRu, descriptionEn, numberOfPlaces, price)
values ('Люкс','Luxury',
 'В номере: ванная, туалет, туалетные принадлежности,
  большая двухспальная кровать, плазменный телевизор, 
  кабельное ТВ, электрический чайник, постельное белье
  и полотенца.Подходит для 1,2,3,4-местного размещения.',
 'The luxurious room includes a king-sized bed, linens, 
  oversized bathroom with bath amenities, a refrigerator, 
  an efficient workspace and free Wi-Fi, cable TV, and 42″
  flat screen TV.',
 4, 1300);

insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 1, 101);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 1, 102);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 1, 103);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 1, 104);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 1, 105);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 1, 106);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 1, 107);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 1, 108);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 1, 109);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 1, 110);

insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 2, 111);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 2, 112);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 2, 113);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 2, 114);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 2, 115);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 2, 116);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 2, 117);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 2, 118);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 2, 119);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 2, 120);

insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 4, 201);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 4, 202);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 4, 203);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 4, 204);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 4, 205);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 4, 206);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 4, 207);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 4, 208);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 4, 209);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 4, 210);

insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 3, 211);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 3, 212);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 3, 213);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 3, 214);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 3, 215);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 3, 216);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 3, 217);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 3, 218);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 3, 219);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 3, 220);

insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 5, 301);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 5, 302);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 5, 303);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 5, 304);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 5, 305);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 5, 306);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 5, 307);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 5, 308);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 5, 309);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 5, 310);

insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 6, 311);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 6, 312);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 6, 313);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 6, 314);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 6, 315);

insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 7, 316);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 7, 317);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 7, 318);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 7, 319);
insert into Rooms(idStatusRoom, idClass, numberOfRoom)
values (1, 7, 320);

