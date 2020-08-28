CREATE DATABASE IF NOT EXISTS MovieCatalogueAnswer;

USE MovieCatalogueAnswer;

CREATE TABLE IF NOT EXISTS Genre (
	GenreID int not null PRIMARY KEY,
    GenreName varchar(30) not null   
);
    
CREATE TABLE IF NOT EXISTS Rating (
	RatingID int PRIMARY KEY,
    RatingName varchar(5) not null
);
    
CREATE TABLE IF NOT EXISTS Director (
	DirectorID int PRIMARY KEY,
    FirstName varchar(30) not null,
    LastName varchar(30) not null,
    BirthDate text
);
    
CREATE TABLE IF NOT EXISTS Actor (
	ActorID int not null PRIMARY KEY,
    FirstName varchar(30) not null,
    LastName varchar(30) not null,
    BirthDate text
);

CREATE TABLE IF NOT EXISTS Movie (
	MovieID int not null PRIMARY KEY,
    GenreID int not null,
		FOREIGN KEY (GenreID) REFERENCES Genre(GenreID),
    DirectorID int,
		FOREIGN KEY (DirectorID) REFERENCES Director(DirectorID),
    RatingID int,
		FOREIGN KEY (RatingID) REFERENCES Rating(RatingID),
    Title varchar(128) not null,
    ReleaseDate text
);
    
CREATE TABLE IF NOT EXISTS CastMember (
	CastMemberID int PRIMARY KEY,
	ActorID int not null,
		FOREIGN KEY (ActorID) REFERENCES Actor(ActorID),
	MovieID int not null,
		FOREIGN KEY (MovieID) REFERENCES Movie(MovieID),
	Role varchar(50) not null
);