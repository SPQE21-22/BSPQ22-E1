-- file for schema and user creation
DROP SCHEMA IF EXISTS libraryDB;
DROP USER IF EXISTS 'spq'@'%';

CREATE SCHEMA libraryDB;
CREATE USER IF NOT EXISTS 'spq'@'%' IDENTIFIED BY 'spq';

GRANT ALL ON libraryDB.* TO 'spq'@'%';