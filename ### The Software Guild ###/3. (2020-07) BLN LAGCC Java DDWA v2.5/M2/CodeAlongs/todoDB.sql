DROP DATABASE IF EXISTS todoDB;
CREATE DATABASE todoDB;

USE todoDB;

CREATE TABLE Game (
gameID INT PRIMARY KEY AUTO_INCREMENT,
answers CHAR(4) NOT NULL,
isFinished BOOLEAN DEFAULT false);

CREATE TABLE Round (
roundID INT PRIMARY KEY AUTO_INCREMENT,
guess CHAR(4) NOT NULL,
result CHAR(7) NOT NULL,
roundtime DATETIME,
gameID INT NOT NULL,
FOREIGN KEY (GameID) REFERENCES Game(GameID));





-- INSERT INTO Game(GameID, Answers, isFinished)
-- VALUES(1, "1243", false),
-- (2, "4365", false),
-- (3, "1234", true);

-- INSERT INTO Round(RoundID, Guess, Result, GameID)
-- VALUES(1, "7890", "3/4", 1),
-- (2, "3465", "1/4", 1),
-- (3, "5678", "2/4", 2);




show tables;
desc game;
select * from game;
desc round;
select * from round;