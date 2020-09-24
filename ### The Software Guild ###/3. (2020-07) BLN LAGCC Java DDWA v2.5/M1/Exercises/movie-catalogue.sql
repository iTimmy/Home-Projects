create database if not exists moviecatalogue;

use moviecatalogue;

create table if not exists Genre (
	GenreID int NOT NULL PRIMARY KEY,
    GenreName VARCHAR(30) NOT NULL
);
insert into Genre (GenreID, GenreName) values
	(1, 'Action'),
    (2, 'Comedy'),
    (3, 'Drama'),
    (4, 'Horror');

create table if not exists Director (
	DirectorID int PRIMARY KEY,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate text
);
insert into Director (DirectorID, FirstName, LastName, BirthDate) values 
	(1, 'Ivan', 'Reitman', '10/27/1946'),
    (2, 'Ted', 'Kotcheff', NULL);

create table if not exists Rating (
	RatingID int PRIMARY KEY,
    RatingName VARCHAR(5) NOT NULL
);
insert into Rating (RatingID, RatingName) values
	(1, 'G'),
    (2, 'PG'),
    (3, 'PG-13'),
    (4, 'R');

create table if not exists Actor (
	ActorID int NOT NULL PRIMARY KEY,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate text
);
insert into Actor (ActorID, FirstName, LastName, BirthDate) values
	(1, 'Bill', 'Murray', '9/21/1950'),
    (2, 'Dan', 'Aykroyd', '7/1/1952'),
    (3, 'John', 'Candy', '10/31/1950'),
    (4, 'Steve', 'Martin', NULL),
    (5, 'Sylvester', 'Stallone', NULL);

create table if not exists Movie (
	MovieID int NOT NULL PRIMARY KEY,       
    GenreID int NOT NULL,
		FOREIGN KEY (GenreID) references Genre (GenreID),
    DirectorID int,
		FOREIGN KEY (DirectorID) references Director (DirectorID),
    RatingID int,
		FOREIGN KEY (RatingID) references Rating (RatingID),
    Title VARCHAR(128),
    ReleaseDate text
);
insert into Movie (MovieID, GenreID, DirectorID, RatingID, Title, ReleaseDate) values
	(1, 1, 2, 4, 'Rambo: First Blood', '10/22/1982'),
    (2, 2, NULL, 4, 'Planes, Trains & Automobiles', '11/25/1987'),
    (3, 2, 1, 2, 'Ghostbusters', NULL),
    (4, 2, NULL, 2, 'The Great Outdoors', '6/17/1988');

create table if not exists CastMembers (
	CastMemberID int PRIMARY KEY,
    ActorID int NOT NULL,
		FOREIGN KEY (ActorID) references Actor (ActorID),
    MovieID int NOT NULL,
		FOREIGN KEY (MovieID) references Movie (MovieID),
    Role VARCHAR(50) NOT NULL
);
insert into CastMembers (CastMemberID, ActorID, MovieID, Role) values
	(1, 5, 1, 'John Rambo'),
    (2, 4, 2, 'Neil Page'),
    (3, 3, 2, 'Del Griffith'),
    (4, 1, 3, 'Dr. Peter Venkman'),
    (5, 2, 3, 'Dr. Raymond Stanz'),
    (6, 2, 4, 'Roman Craig'),
    (7, 3, 4, 'Chet Ripley');
    
DELETE from CastMembers
WHERE MovieID = 1;

DELETE from Movie
WHERE MovieID = 1; 
    
UPDATE Movie SET
	Title = 'Ghostbusters (1984)',
    ReleaseDate = '6/8/1984'
WHERE MovieID = 3;
    
UPDATE Genre SET
	GenreName = 'Action/Adventure'
WHERE GenreID = 1;

ALTER TABLE Actor
	ADD DateOfDeath text;
    
UPDATE Actor SET
	BirthDate = '3/4/1994'
WHERE ActorID = 3;