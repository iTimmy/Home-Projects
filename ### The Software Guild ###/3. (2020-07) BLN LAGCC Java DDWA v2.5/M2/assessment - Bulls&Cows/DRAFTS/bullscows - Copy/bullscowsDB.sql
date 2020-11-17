DROP DATABASE IF EXISTS bullscows;
CREATE DATABASE bullscows;

USE bullscows;

CREATE TABLE User (
	userID INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100)
);

CREATE TABLE Game (
	gameID INT PRIMARY KEY AUTO_INCREMENT,
    answers VARCHAR(4),
    isFinished BOOLEAN,
    userID INT,
    FOREIGN KEY (userID) REFERENCES User(userID)
);

CREATE TABLE Round (
	roundID INT PRIMARY KEY AUTO_INCREMENT,
	guess VARCHAR(4),
    result VARCHAR(4),
    gameID INT,
    roundTime DATE,
    FOREIGN KEY (gameID) REFERENCES Game(gameID)
);