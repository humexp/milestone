CREATE SCHEMA milestone;
CREATE USER milestone;
ALTER ROLE milestone PASSWORD '123456';
GRANT  ALL  ON SCHEMA milestone TO milestone;