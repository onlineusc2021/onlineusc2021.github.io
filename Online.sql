CREATE DATABASE IF NOT EXISTS LineQueue;
USE LineQueue;
CREATE TABLE IF NOT EXISTS Queue (
	userID int,
    park varchar(255),
    location varchar(255)
);