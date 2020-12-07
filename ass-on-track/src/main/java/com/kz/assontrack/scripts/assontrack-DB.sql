DROP DATABASE IF EXISTS assontrack;

CREATE DATABASE IF NOT EXISTS assontrack;

USE assontrack;

CREATE TABLE Week (
	weekID INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE Day (
	dayID VARCHAR(10) PRIMARY KEY,
    mealID INT NOT NULL
);

CREATE TABLE DayMeal (
	dayID VARCHAR(10) NOT NULL,
	mealID int NOT NULL,
	FOREIGN KEY (dayID) REFERENCES Day(dayID),
	FOREIGN KEY (mealID) REFERENCES Meal(mealID)
);

CREATE TABLE Meal (
	mealID INT PRIMARY KEY AUTO_INCREMENT,
    mealType VARCHAR(10) NOT NULL,
    mealName VARCHAR(10) NULL,
    mealIngredients text NOT NULL,
    dayID VARCHAR(10) NOT NULL
);

INSERT INTO Day (
	dayID,
    mealID
)
VALUES
("Sunday", 1),
("Monday", 1),
("Tuesday", 1),
("Wednesday", 1),
("Thursday", 1),
("Friday", 1),
("Saturday", 1);

INSERT INTO meal (
	mealType,
    mealName,
    mealIngredients,
    dayID
)
VALUES
("BREAKFAST", "FAVORITE", "greek yogurt (180g), raspberries (8), whey protein (1/2)", "Sunday");



show tables;

desc week;
desc day;
desc meal;

select * from Week;
select * from Day;
select * from Meal
INNER JOIN Day ON Meal.dayID = Day.dayID