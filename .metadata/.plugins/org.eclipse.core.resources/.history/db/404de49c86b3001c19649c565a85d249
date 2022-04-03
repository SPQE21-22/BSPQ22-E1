/* DELETE 'facebook_user' database*/
DROP SCHEMA IF EXISTS facebookDb;
/* DELETE USER 'facebook_user' AT LOCAL SERVER*/
DROP USER IF EXISTS 'facebook_user'@'%';

/* CREATE 'facebookDb' DATABASE */
CREATE SCHEMA facebookDb;
/* CREATE THE USER 'facebook_user' AT LOCAL SERVER WITH PASSWORD 'password' */
CREATE USER 'facebook_user'@'%' IDENTIFIED BY 'password';
/* GRANT FULL ACCESS TO THE DATABASE 'facebookDb' FOR THE USER 'facebook_user' AT LOCAL SERVER*/
GRANT ALL ON facebookDb.* TO 'facebook_user'@'%';
