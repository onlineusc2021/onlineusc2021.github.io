CREATE DATABASE IF NOT EXISTS LineQueue;
USE LineQueue;
CREATE TABLE IF NOT EXISTS Queue (
	username varchar(255),
    park varchar(255),
    location varchar(255),
    time_stamp varchar(255)
);

CREATE TABLE IF NOT EXISTS Users (
	userID int,
	username varchar(255),
    firstname varchar(255),
    lastname varchar(255),
    userpassword varchar(255)
);
