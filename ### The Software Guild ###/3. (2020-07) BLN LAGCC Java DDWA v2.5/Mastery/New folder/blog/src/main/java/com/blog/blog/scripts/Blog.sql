DROP DATABASE IF EXISTS blog;
CREATE DATABASE IF NOT EXISTS blog;

USE blog;



CREATE TABLE Profile (
	profileID INT PRIMARY KEY AUTO_INCREMENT,
    icon VARCHAR(255) NULL,
    cover VARCHAR(255) NULL,
    wallpaper VARCHAR(255) NULL
);

CREATE TABLE Users (
	userID INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(12) NOT NULL,
    lastName VARCHAR(12) NOT NULL,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(500) NOT NULL,
	enabled BOOLEAN NOT NULL,
    profileID INT NOT NULL,
    FOREIGN KEY (profileID) REFERENCES Profile(profileID)
);

CREATE TABLE Roles (
	roleID INT PRIMARY KEY AUTO_INCREMENT,
	role VARCHAR(30)
);

CREATE TABLE UsersRoles (
userID INT NOT NULL,
roleID INT NOT NULL,
PRIMARY KEY (userID, roleID),
FOREIGN KEY (userID) REFERENCES Users(userID),
FOREIGN KEY (roleID) REFERENCES Roles(roleID)
);

CREATE TABLE Tags (
	tagID INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Blogs (
	blogID INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL UNIQUE,
    date DATE NOT NULL,
    content TEXT NULL,
    userID INT NOT NULL,
    FOREIGN KEY (userID) REFERENCES Users(userID),
	approved BOOLEAN NOT NULL,
    postDate DATE NULL,
    expirationDate DATE NULL,
    photo VARCHAR(255) NULL
);

CREATE TABLE BlogsTags (
	blogID INT NOT NULL,
    tagID INT NOT NULL,
    FOREIGN KEY (blogID) REFERENCES Blogs(blogID),
	FOREIGN KEY (tagID) REFERENCES Tags(tagID)
);






INSERT INTO Profile (
	profileID,
    icon,
    cover,
    wallpaper
)
VALUES
(1, "default-icon.jpg", "default-cover.jpg", "default-wallpaper.jpg"),
(2, "default-icon.jpg", "default-cover.jpg", "default-wallpaper.jpg");

INSERT INTO Users (
userID,
firstName,
lastName,
username,
password,
enabled,
profileID
) 
VALUES
(1, "Timothy", "Amarok", "admin", "$2a$04$N0WzLB5s3qJvAnSHWu5vhuzrX362PHXAYjciCkK7jUtf32bESN.Tu", true, 1),
(2, "Timothy", "Amarok", "timmy", "$2a$04$N0WzLB5s3qJvAnSHWu5vhuzrX362PHXAYjciCkK7jUtf32bESN.Tu", false, 2);

INSERT INTO Roles (
	roleID,
    role
) 
VALUES
(1, "ROLE_ADMIN"),
(2, "ROLE_USER"),
(3, "ROLE_VIEWER"),
(4, "ROLE_GUEST");

INSERT INTO UsersRoles (
	roleID,
    userID
) 
VALUES
(1, 1),
(2, 2);

INSERT INTO Tags(
tagID,
name
) 
VALUES
(1, "tagOne"),
(2, "tagTwo"),
(3, "tagThree");

INSERT INTO Blogs(
blogID,
title,
date,
content,
userID,
approved,
postDate,
expirationDate,
photo
) 
VALUES
(1, "blogOne", CURRENT_TIMESTAMP, "blogOne", 1, true, NULL, NULL, NULL),
(2, "blogTwo", CURRENT_TIMESTAMP, "blogTwo", 1, true, NULL, NULL, NULL),
(3, "blogThree", CURRENT_TIMESTAMP, "blogThree", 1, true, NULL, NULL, NULL),
(4, "blogFour", CURRENT_TIMESTAMP, "blogFour", 1, true, NULL, NULL, NULL),
(5, "blogFive", CURRENT_TIMESTAMP, "blogFive", 2, true, NULL, NULL, NULL),
(6, "blogSix", CURRENT_TIMESTAMP, "blogSix", 2, true, NULL, NULL, NULL),
(7, "blogSeven", CURRENT_TIMESTAMP, "blogSeven", 2, false, NULL, NULL, NULL),
(8, "blogEight", CURRENT_TIMESTAMP, "blogEight", 2, false, NULL, NULL, NULL);

INSERT INTO BlogsTags (
blogID,
tagID
) 
VALUES 
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(3, 1),
(4, 1);







SHOW TABLES;
SELECT * FROM Blogs;
SELECT * FROM Tags;

SELECT * FROM Blogs
INNER JOIN Tags ON Tags.tagID = Blogs.blogID;

SELECT * FROM Tags
INNER JOIN Blogs ON Blogs.blogID = Tags.tagID;

SELECT * FROM BlogsTags;

SELECT * FROM Blogs
inner join BlogsTags on Blogs.blogID = BlogsTags.blogID
inner join Tags on BlogsTags.tagID = Tags.tagID;







SELECT * FROM Blogs
inner join BlogsTags on Blogs.blogID = BlogsTags.blogID
inner join Tags on BlogsTags.tagID = Tags.tagID
WHERE title = "blogOne";

SELECT name, Tags.tagID FROM Tags
INNER JOIN BlogsTags on BlogsTags.tagID = Tags.tagID
WHERE BlogsTags.blogID = 1;