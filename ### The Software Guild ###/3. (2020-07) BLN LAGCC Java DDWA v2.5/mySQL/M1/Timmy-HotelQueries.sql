select StartDate, EndDate, FirstName from Reservation, Guest
where EndDate BETWEEN '2023-07-01' AND '2023-07-31';

select FirstName, StartDate from Reservation, Guest, Amenities
where AmenitiesName IN ("jacuzzi");

select * from Reservation, RoomType, Amenities, Guest
where FirstName = 'Timmy';

select ReservationID, TotalRoomCost from Reservation;

select RoomNumber from Reservation
where Adults + Children = 3 AND StartDate BETWEEN '2023-04-01' AND '2023-04-31';

select FirstName from Guest
ORDER BY count(FirstName) AND LastName;

select FirstName, Address, PhoneNumber from Guest
where PhoneNumber = 12345678;

