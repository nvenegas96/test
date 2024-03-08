CREATE SCHEMA IF NOT EXISTS sentra;
SET SCHEMA sentra;
CREATE TABLE users ( 
	userID VARCHAR(255) PRIMARY KEY, 
	name VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE, 
	password VARCHAR(100) NOT NULL, 
	created TIMESTAMP,
	modified TIMESTAMP,
	lastlogin TIMESTAMP,
	token VARCHAR(255),
	active boolean);
CREATE TABLE phones (phoneID VARCHAR(255) PRIMARY KEY, 
	number VARCHAR(10) NOT NULL, 
	citycode VARCHAR(10) NOT NULL,
	contrycode VARCHAR(10) NOT NULL,
	userID VARCHAR(255),
	FOREIGN KEY (userID) REFERENCES users(userID));
CREATE TABLE roles ( roleID INT PRIMARY KEY,
	name VARCHAR(45));
CREATE TABLE user_roles ( userID VARCHAR(255),
	roleID INT, 
	FOREIGN KEY (roleID) REFERENCES roles(roleID), 
	FOREIGN KEY (userID) REFERENCES users(userID) );
/*
INSERT INTO roles VALUES (1,'ROLE_ADMIN');
INSERT INTO roles VALUES (2,'ROLE_USER');

INSERT INTO phones VALUES (1,'1234567','1','57');
INSERT INTO users VALUES (1,'Juan Rodriguez', 'juan@rodriguez.org', 'hunter2',1);
INSERT INTO user_roles VALUES (1,1);*/