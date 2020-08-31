insert into Guest (
GuestID,
FirstName,
LastName,
State,
City,
ZipCode,
Address,
PhoneNumber
) 
values
(05, "Timmy", "Tim", "NY", "Brooklyn", 90023, "245 Silver lake city", 1234567890),
(01, "Mack", "Simmer", "IA", "Council Bluffs", 51501, "379 Old Shore Street", 2915530508),
(02, "Bettyann", "Seery", "AK", "Wasilla", 99654, "750 Wintergreen Dr.", 4782779632),
(03, "Duane", "Cullison", "TX", "Harlingen", 78552, "	9662 Foxrun Lane", 3084940198),
(04, "Karie", "Yang", "NJ", "West Deptford", 08096, "9378 W. Augusta Ave.", 2147300298),
(06, "Aurore", "Lipton", "MI", "Saginaw", 48601	, "762 Wild Rose Street", 3775070974),
(07, "Zachery", "Luechtefeld", "CO", "Arvada", 80003, "7 Poplar Dr.", 8144852615),
(08, "Jeremiah", "Pendergrass", "IL", "Zion", 60099, "70 Oakwood St.", 2794910960),
(09, "Walter", "Holaway", "RI", "Cumberland", 02864, "7556 Arrowhead St.", 4463966785),
(10, "Wilfred", "Vise", "NY", "Oswego", 13126, "77 West Surrey Street", 8347271001),
(11, "Maritza", "Tilton", "VA", "Burke", 22015	, "939 Linda Rd.", 4463516860),
(12, "Joleen", "Tison", "PA", "Drexel Hill", 19026, "87 Queen St.", 2318932755);



insert into RoomType (
RoomTypeName,
StandardOccupancy,
MaximumOccupancy,
ExtraPerson,
BasePrice
) 
values
("Double", 2, 4, 10.00, 199.99),
("Double", 2, 4, 10.00, 174.99),
("Double", 2, 4, 10.00, 199.99),
("Double", 2, 4, 10.00, 174.99),
("Single", 2, 2, 00.00, 174.99),
("Single", 2, 2, 00.00, 149.99),
("Single", 2, 2, 00.00, 174.99),
("Single", 2, 2, 00.00, 149.99),
("Double", 2, 4, 10.00, 199.99),
("Double", 2, 4, 10.00, 174.99),
("Double", 2, 4, 10.00, 199.99),
("Double", 2, 4, 10.00, 174.99),
("Single", 2, 2, 00.00, 174.99),
("Single", 2, 2, 00.00, 149.99),
("Single", 2, 2, 00.00, 174.99),
("Single", 2, 2, 00.00, 149.99),
("Suite", 3, 8, 20.00, 399.99),
("Suite", 3, 8, 20.00, 399.99);



insert into Amenities (
AmenitiesName,
ExtraPrice
) 
values
("Microwave, Jacuzzi", 15.00),
("Refrigerator", 00.00),
("Microwave, Jacuzzi", 15.00),
("Refrigerator", 00.00),
("Microwave, Refrigerator, Jacuzzi", 25.00),
("Microwave, Refrigerator", 15.00),
("Microwave, Refrigerator, Jacuzzi", 25.00),
("Microwave, Refrigerator", 00.00),
("Microwave, Jacuzzi", 15.00),
("Refrigerator", 00.00),
("Microwave, Jacuzzi", 15.00),
("Refrigerator", 00.00),
("Microwave, Refrigerator, Jacuzzi", 25.00),
("Microwave, Refrigerator", 15.00),
("Microwave, Refrigerator, Jacuzzi", 25.00),
("Microwave, Refrigerator", 15.00),
("Microwave, Refrigerator, Oven", 25.00),
("Microwave, Refrigerator, Oven", 25.00);


insert into Reservation (
RoomNumber,
StartDate,
EndDate,
Adults,
Children,
TotalRoomCost,
GuestID,
RoomID
)
values
(308, "2023-02-02", "2023-02-04", 1, 0, 299.98, 01, 1),
(203, "2023-02-05", "2023-02-10", 2, 1, 999.95, 02, 2),
(305, "2023-02-22", "2023-02-24", 2, 0, 349.98, 03, 3),
(201, "2023-03-06", "2023-03-07", 2, 2, 199.99, 04, 4),
(307, "2023-03-17", "2023-03-20", 1, 1, 524.97, 05, 5),
(302, "2023-03-29", "2023-03-31", 2, 2, 924.95, 06, 6),
(202, "2023-03-31", "2023-04-05", 2, 0, 349.98, 07, 7),
(304, "2023-04-09", "2023-04-13", 1, 0, 874.95, 08, 8),
(301, "2023-04-23", "2023-04-24", 1, 0, 799.96, 09, 9),
(204, "2023-04-23", "2023-04-24", 1, 1, 174.99, 010, 10),
(401, "2023-05-30", "2023-06-02", 2, 4, 1199.97, 011, 11),
(206, "2023-06-10", "2023-06-14", 2, 0, 599.96, 012, 12),
(208, "2023-06-10", "2023-06-14", 1, 0, 599.96, 013, 13),
(304, "2023-06-17", "2023-06-18", 3, 0, 184.99, 014, 14),
(205, "2023-06-28", "2023-07-02", 2, 0, 699.96, 015, 15),
(204, "2023-07-13", "2023-07-14", 3, 1, 184.99, 016, 16),
(401, "2023-07-18", "2023-07-21", 4, 2, 1259.97, 017, 17),
(303, "2023-07-28", "2023-07-29", 2, 1, 199.99, 018, 18),
(305, "2023-08-30", "2023-09-01", 1, 0, 349.98, 019, 19),
(209, "2023-09-16", "2023-09-17", 2, 0, 149.99, 020, 20),
(203, "2023-09-13", "2023-09-15", 2, 2, 399.98, 021, 21),
(401, "2023-11-22", "2023-11-25", 2, 2, 1199.97, 022, 22),
(206, "2023-11-22", "2023-11-25", 2, 0, 449.97, 023, 23),
(301, "2023-11-22", "2023-11-25", 2, 2, 599.97, 024, 24),
(302, "2023-12-24", "2023-12-28", 2, 0, 699.96, 025, 25);


        
SET SQL_SAFE_UPDATES = 0;

delete from Guest
where FirstName = 'Jeremiah' AND LastName = 'Pendergrass';

SET SQL_SAFE_UPDATES = 1;