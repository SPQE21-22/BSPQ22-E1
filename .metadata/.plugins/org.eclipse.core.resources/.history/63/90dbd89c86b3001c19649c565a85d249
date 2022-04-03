/* DELETE 'google_user' database*/
DROP SCHEMA IF EXISTS googleDb;
/* DELETE USER 'google_user' AT LOCAL SERVER*/
DROP USER IF EXISTS 'google_user'@'%';

/* CREATE 'googledb' DATABASE */
CREATE SCHEMA googleDb;
/* CREATE THE USER 'google_user' AT LOCAL SERVER WITH PASSWORD 'password' */
CREATE USER 'google_user'@'%' IDENTIFIED BY 'password';
/* GRANT FULL ACCESS TO THE DATABASE 'googledb' FOR THE USER 'google_user' AT LOCAL SERVER*/
GRANT ALL ON googleDb.* TO 'google_user'@'%';
