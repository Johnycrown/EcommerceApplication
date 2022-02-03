create  database pheonix_db;

create user 'phoenix_user'@'localhost' identified by 'pass_123';
grant all privileges  on pheonix_db.* to 'phoenix_user'@'localhost';
flush privileges;