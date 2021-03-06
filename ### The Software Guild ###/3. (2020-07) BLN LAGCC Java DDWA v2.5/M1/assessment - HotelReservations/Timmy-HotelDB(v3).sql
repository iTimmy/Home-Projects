drop database if exists HotelReservations;

create database if not exists HotelReservations;

use HotelReservations;

create table Guest (
GuestID int PRIMARY KEY auto_increment,
FirstName varchar(50) NOT NULL,
LastName varchar(50) NOT NULL,
State char(2) NOT NULL,
City varchar(30) NOT NULL,
ZipCode char(5) NOT NULL,
Address varchar(100) NOT NULL,
PhoneNumber char(10) NOT NULL
);

create table RoomType (
RoomTypeID int PRIMARY KEY AUTO_INCREMENT,
RoomTypeName char(6) NOT NULL,
StandardOccupancy int NOT NULL,
MaximumOccupancy int NOT NULL,
ExtraPerson decimal (4,2) NULL,
BasePrice decimal(5,2) NOT NULL
);

create table Room (
RoomID int PRIMARY KEY,
RoomTypeID int NOT NULL
);

create table Amenities (
AmenitiesID int PRIMARY KEY AUTO_INCREMENT,
ExtraPrice decimal(5,2) NULL,
AmenitiesName varchar(32) NOT NULL
);

create table RoomAmenities (
RoomID int NOT NULL,
AmenitiesID int NOT NULL,
FOREIGN KEY (RoomID) REFERENCES Room(RoomID),
FOREIGN KEY (AmenitiesID) REFERENCES Amenities(AmenitiesID)
);

create table Reservation (
ReservationID int PRIMARY KEY auto_increment,
StartDate date NOT NULL,
EndDate date NOT NULL,
Adults int NOT NULL,
Children int NULL,
TotalRoomCost decimal(6,2) NOT NULL,
GuestID int not null,
RoomID int not null
);

ALTER TABLE `Reservation` 
ADD CONSTRAINT `fk_ReservationGuest` 
FOREIGN KEY (`GuestID`) 
REFERENCES `Guest`(`GuestID`) ON DELETE NO ACTION;

ALTER TABLE `Reservation` 
ADD CONSTRAINT `fk_ReservationRoom` 
FOREIGN KEY (`RoomID`) 
REFERENCES `Room`(`RoomID`) ON DELETE NO ACTION;

select
Reservation.ReservationID,
Reservation.GuestID,
Guest.GuestID,
RoomType.RoomTypeID
from Reservation
INNER JOIN Guest ON Reservation.GuestID = Guest.GuestID
INNER JOIN RoomType ON Reservation.RoomID = RoomType.RoomTypeID
