CREATE DATABASE IF NOT EXISTS LineQueue;
USE LineQueue;
CREATE TABLE IF NOT EXISTS Queue (
	userID int,
    park varchar(255),
    location varchar(255)
);

CREATE TABLE IF NOT EXISTS UserTable (
	userID int,
    firstname varchar(255),
    lastname varchar(255),
    userpassword varchar(255)
);
