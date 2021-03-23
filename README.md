# TicTacToe

TicTacToe against an ai that insults you

This is a practice project for jdbc

I used xampp mysql with the default 3306 port and sqlyog community,

If you wish to run it you will have to create the database yourself,


// this is the sql query for the database

DROP DATABASE IF EXISTS robot;

CREATE DATABASE robot;

CREATE USER 'username' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON robot TO 'username';

CREATE TABLE roboti(
id INT NOT NULL AUTO_INCREMENT,
ime VARCHAR(255),
PRIMARY KEY(id)
);

CREATE TABLE uvrede(
id INT NOT NULL AUTO_INCREMENT,
uvredaID INT NOT NULL,
uvreda VARCHAR(255),
FOREIGN KEY (uvredaID) REFERENCES roboti(id),
PRIMARY KEY(id)
);

//
