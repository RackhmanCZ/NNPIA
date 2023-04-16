CREATE TABLE app_user (
   id int4 PRIMARY KEY,
   username varchar(255) ,
   password varchar(255) ,
   active BOOL,
   creation_date DATE,
   update_date DATE
);