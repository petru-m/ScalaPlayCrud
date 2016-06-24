# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

CREATE TABLE "address"(
  "addressId" int(11) NOT NULL AUTO_INCREMENT,
  "streetName" VARCHAR (45) DEFAULT NULL ,
  "number" int(11) DEFAULT NULL,
  "locality" VARCHAR (45) DEFAULT NULL ,
  "city" VARCHAR (45) DEFAULT NULL ,
  "country" VARCHAR (45) DEFAULT NULL ,
  PRIMARY KEY ("addressId")
);

 CREATE TABLE "user" (
  "userId" int(11) NOT NULL AUTO_INCREMENT,
  "first_name" varchar(45) DEFAULT NULL,
  "last_name" varchar(45) DEFAULT NULL,
  "email" varchar(45) DEFAULT NULL,
  "addressId" int(11) DEFAULT NULL ,
  PRIMARY KEY ("userId"),
  FOREIGN KEY ("addressId") REFERENCES "address"("addressId")
);



# --- !Downs`

DROP TABLE "address";
DROP TABLE "user";