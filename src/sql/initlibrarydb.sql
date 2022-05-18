ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';

DROP SCHEMA IF EXISTS librarydb;

CREATE SCHEMA librarydb;

GRANT ALL ON librarydb.* TO 'root'@'localhost';

show databases;
