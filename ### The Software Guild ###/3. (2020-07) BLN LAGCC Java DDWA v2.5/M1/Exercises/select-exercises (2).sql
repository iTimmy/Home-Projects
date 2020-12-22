USE PersonalTrainer;

-- Select all rows and columns from the Exercise table.
-- 64 rows
--------------------
SELECT * FROM Exercise;

-- Select all rows and columns from the Client table.
-- 500 rows
--------------------
select * from Client

-- Select all columns from Client where the City is Metairie.
-- 29 rows
--------------------
select City from Client
where City = "Metairie"
LIMIT 0, 29

-- Is there a Client with the ClientId '818u7faf-7b4b-48a2-bf12-7a26c92de20c'?
-- nope
--------------------
select ClientId from Client
where ClientId = '818u7faf-7b4b-48a2-bf12-7a26c92de20c'

-- How many rows in the Goal table?
-- 17 rows
--------------------
select count(*) from Goal
LIMIT 0, 17

-- Select Name and LevelId from the Workout table.
-- 26 rows
--------------------
select Name, LevelId from Workout
LIMIT 0, 26

-- Select Name, LevelId, and Notes from Workout where LevelId is 2.
-- 11 rows
--------------------
select Name, LevelId, Notes from Workout
where LevelId = 2
LIMIT 0, 11

-- Select FirstName, LastName, and City from Client 
-- where City is Metairie, Kenner, or Gretna.
-- 77 rows
--------------------
select FirstName, LastName, City from Client
where City in ("Matarie", "Kenner") or City in ("Gretna")
LIMIT 0, 77

-- Select FirstName, LastName, and BirthDate from Client
-- for Clients born in the 1980s.
-- 72 rows
--------------------
select FirstName, LastName, BirthDate from Client
where BirthDate like ("1980")
LIMIT 0, 72

-- Write the query above in a different way. 
-- If you used BETWEEN, you can't use it again.
-- If you didn't use BETWEEN, use it!
-- Still 72 rows
--------------------

-- How many rows in the Login table have a .gov EmailAddress?
-- 17 rows
--------------------
select count(*) from Login
where * = ("_.gov")
LIMIT 0, 17

-- How many Logins do NOT have a .com EmailAddress?
-- 122 rows
--------------------
select count(*) from Login
where * != "_.com"
LIMIT 0, 122

-- Select first and last name of Clients without a BirthDate.
-- 37 rows
--------------------
select FirstName, LastName from Clients
LIMIT 0, 37

-- Select the Name of each ExerciseCategory that has a parent.
-- (ParentCategoryId value is not null)
-- 12 rows
--------------------
select Name, ParentCategoryId from ExerciseCategory
where ParentCategoryId != null
LIMIT 0, 12

-- Select Name and Notes of each level 3 Workout that
-- contains the word 'you' in its Notes.
-- 4 rows
--------------------
select Name, Notes from Workout
where LevelId = 3 and Notes like ("you")
LIMIT 0, 4

-- Select FirstName, LastName, City from Clients who have a LastName
-- that starts with L,M, or N and who live in LaPlace.
-- 5 rows
--------------------
select FirstName, LastName, City from Clients
where FirstName like ("L%") and LastName like ("M%") or LastName like ("N%") and City in ('LaPlace')
LIMIT 0, 5

-- Select InvoiceId, Description, Price, Quantity, ServiceDate 
-- and the line item total, a calculated value, where the line item total
-- is between 15 and 25 dollars.
-- 667 rows
--------------------
select Sum(InvoiceId, Description, Price, Quantity, ServiceDate) from Clients
where Price between 15 and 25
LIMIT 0, 677

-- Does the Client, Estrella Bazely, have a Login? 
-- If so, what is her email address?
-- This requires two queries:
-- First, select a Client record for Estrella Bazely. Does it exist?
-- Second, if it does, select a Login record that matches its ClientId.
--------------------
select Client, Login 
where Login is not NULL and Client in "_@_"

-- What are the Goals of the Workout with the Name 'This Is Parkour'?
-- You need three queries!:
-- 1. Select the WorkoutId from Workout where Name equals 'This Is Parkour'.
-- 2. Select GoalId from WorkoutGoal where the WorkoutId matches the WorkoutId
--    from your first query.
-- 3. Select everything from Goal where the GoalId is one of the GoalIds from your
--    second query.
-- 1 row, 3 rows, 3 rows
--------------------
