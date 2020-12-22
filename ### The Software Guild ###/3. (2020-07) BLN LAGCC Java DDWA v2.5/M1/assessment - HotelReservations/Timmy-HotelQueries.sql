/* Write a query that returns a list of reservations that end in July 2023, including the name of the guest, the room number(s), and the reservation dates. */
select RoomID, StartDate, EndDate, FirstName from Reservation
inner join Guest on Reservation.GuestID = Guest.GuestID
where EndDate BETWEEN '2023-07-01' AND '2023-07-31';

/* Write a query that returns a list of all reservations for rooms with a jacuzzi, displaying the guest's name, the room number, and the dates of the reservation. */
select Reservation.RoomID, AmenitiesName, FirstName, StartDate, EndDate from Reservation
inner join Guest on Reservation.GuestID = Guest.GuestID
inner join RoomAmenities on Reservation.RoomID = RoomAmenities.RoomID
inner join Amenities on RoomAmenities.AmenitiesID = Amenities.AmenitiesID
where AmenitiesName = "jacuzzi";

/* Write a query that returns all the rooms reserved for a specific guest, including the guest's name, the room(s) reserved, the starting date of the reservation, and how many people were included in the reservation. (Choose a guest's name from the existing data.) */
select RoomID, FirstName, StartDate, Adults, Children from Reservation
inner join Guest on Reservation.GuestID = Guest.GuestID
where Guest.FirstName = 'Timmy';

/* Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. The results should include all rooms, whether or not there is a reservation associated with the room. */
select Reservation.RoomID, ReservationID, TotalRoomCost from Reservation
RIGHT JOIN Room ON Room.RoomID = Reservation.RoomID
ORDER BY ReservationID ASC;

/* Write a query that returns all the rooms accommodating at least three guests and that are reserved on any date in April 2023. */
select Adults, Children, RoomID, FirstName, StartDate, EndDate from Reservation
join Guest on Reservation.GuestID = Guest.GuestID
where StartDate BETWEEN '2023-04-01' AND '2023-04-31' AND EndDate BETWEEN '2023-04-01' AND '2023-04-31' AND Children + Adults = 1;

/* Write a query that returns a list of all guest names and the number of reservations per guest, sorted starting with the guest with the most reservations and then by the guest's last name. */
SELECT g.FirstName, count(g.GuestID) FROM Guest g 
INNER JOIN Reservation r on g.GuestID = r.GuestID GROUP BY g.FirstName
ORDER BY count(g.GuestID) DESC, FirstName ASC;

/* Write a query that displays the name, address, and phone number of a guest based on their phone number. (Choose a phone number from the existing data.) */
select FirstName, Address, PhoneNumber from Guest
where PhoneNumber = 1234567890;

