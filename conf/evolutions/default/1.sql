# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

 CREATE TABLE "user" (
  "userId" int(11) NOT NULL AUTO_INCREMENT,
  "first_name" varchar(45) DEFAULT NULL,
  "last_name" varchar(45) DEFAULT NULL,
  "email" varchar(45) DEFAULT NULL,
  PRIMARY KEY ("userId")
);

# --- !Downs

DROP TABLE "user";