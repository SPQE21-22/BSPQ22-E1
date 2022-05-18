DROP SCHEMA IF EXISTS librarydb;

CREATE SCHEMA librarydb;

CREATE USER IF NOT EXISTS root@'localhost' IDENTIFIED BY root;

GRANT ALL ON librarydb.* TO root@'localhost';
