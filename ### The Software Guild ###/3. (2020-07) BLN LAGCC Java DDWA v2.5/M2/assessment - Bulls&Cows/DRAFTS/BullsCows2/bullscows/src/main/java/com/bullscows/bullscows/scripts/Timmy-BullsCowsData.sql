drop database if exists BullsCows;

create database if not exists BullsCows;

use BullsCows;

create table if not exists Game (
GameID int PRIMARY KEY NOT NULL auto_increment,
Answers int NULL,
isFinished boolean NULL
);

create table if not exists Round (
RoundID int PRIMARY KEY NOT NULL auto_increment,
GameID int NOT NULL,
FOREIGN KEY (GameID) REFERENCES Game(GameID),
Guess VARCHAR(4) NULL,
Result VARCHAR(4) NULL,
RoundTime Date NOT NULL
);