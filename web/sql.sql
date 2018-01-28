DROP DATABASE lakbay_db;
CREATE DATABASE lakbay_db;
USE lakbay_db;

CREATE TABLE place (
     place_ID          INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
     place_name          VARCHAR(255) NOT NULL,
     description          VARCHAR(4097) NOT NULL,
     latitude          FLOAT NOT NULL,
     longitude          FLOAT NOT NULL,
     place_type          VARCHAR(1) NOT NULL DEFAULT 'S',
     
     CHECK(place_type in ('S', 'C'))
);

CREATE TABLE city (
     city_ID               INT NOT NULL UNIQUE PRIMARY KEY,
     
     FOREIGN KEY(city_ID) REFERENCES place(place_ID)
);

CREATE TABLE stop (
     stop_ID               INT NOT NULL UNIQUE PRIMARY KEY,
     city_ID               INT NOT NULL,
     points               INT NOT NULL,
     
     FOREIGN KEY(stop_ID) REFERENCES place(place_ID),
     FOREIGN KEY(city_ID) REFERENCES city(city_ID)
);

CREATE TABLE account (
     account_ID      INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
     account_type    VARCHAR(1) NOT NULL DEFAULT 'P',
     account_email     VARCHAR(255) NOT NULL,

     CHECK(account_type in ('P', 'A'))
);

CREATE TABLE administrator (
     administrator_ID     INT NOT NULL UNIQUE PRIMARY KEY,
     city_ID                    INT NOT NULL UNIQUE,
     
     FOREIGN KEY(administrator_ID) REFERENCES account(account_ID)
);

CREATE TABLE player (
     player_ID          INT NOT NULL UNIQUE PRIMARY KEY,
     player_level     INT NOT NULL,
     player_xp          INT NOT NULL,
     
     FOREIGN KEY(player_ID) REFERENCES account(account_ID)
);

CREATE TABLE credentials (
     account_ID  INT NOT NULL UNIQUE PRIMARY KEY,
     password    VARCHAR(255) NOT NULL,

     FOREIGN KEY(account_ID) REFERENCES account(account_ID)
);

CREATE TABLE quest (
     quest_ID          INT NOT NULL UNIQUE PRIMARY KEY,
     city_ID               INT NOT NULL,
     quest_name          VARCHAR(255) NOT NULL,
     points               INT NOT NULL,

     FOREIGN KEY(city_ID) REFERENCES city(city_ID)
);

CREATE TABLE stop_tag (
     tag_ID               INT NOT NULL UNIQUE PRIMARY KEY,
     tag_name          VARCHAR(255) NOT NULL,
     stop_ID               INT NOT NULL,
     
     FOREIGN KEY(stop_ID) REFERENCES stop(stop_ID)
);

CREATE TABLE quest_tag (
     tag_ID               INT NOT NULL UNIQUE PRIMARY KEY,
     tag_name          VARCHAR(255) NOT NULL,
     quest_ID          INT NOT NULL,
     
     FOREIGN KEY(quest_ID) REFERENCES quest(quest_ID)
);

CREATE TABLE quest_stop (
     quest_ID          INT NOT NULL,
     stop_ID               INT NOT NULL,
     
     FOREIGN KEY(quest_ID) REFERENCES quest(quest_ID),
     FOREIGN KEY(stop_ID) REFERENCES stop(stop_ID)
);

CREATE TABLE visited_stop (
     player_ID          INT NOT NULL,
     stop_ID               INT NOT NULL,
     
     FOREIGN KEY(player_ID) REFERENCES player(player_ID),
     FOREIGN KEY(stop_ID) REFERENCES stop(stop_ID)
);

CREATE TABLE ongoing_quest (
     quest_ID          INT NOT NULL,
     player_ID          INT NOT NULL,
     
     FOREIGN KEY(player_ID) REFERENCES player(player_ID),
     FOREIGN KEY(quest_ID) REFERENCES quest(quest_ID)
);

CREATE TABLE finished_quest (
     quest_ID          INT NOT NULL,
     player_ID          INT NOT NULL,
     
     FOREIGN KEY(player_ID) REFERENCES player(player_ID),
     FOREIGN KEY(quest_ID) REFERENCES quest(quest_ID)
);

INSERT INTO account (account_type, account_email) VALUES ('A', 'account@email.com');
INSERT INTO credentials (account_ID, password) VALUES (1, 'password');
INSERT INTO place (place_name, description, latitude, longitude, place_type) VALUES ('arrneyo', 'kul kids', 100, 100, 'C');
INSERT INTO place (place_name, description, latitude, longitude, place_type) VALUES ('Quezon City', 'City named Quezon', 14.67, 121.04, 'C');
INSERT INTO place (place_name, description, latitude, longitude, place_type) VALUES ('Manila City', 'The capital of the Philippines. This city is filled with various places that embodies the Philippines of the past.', 14.59, 120.98, 'C');
INSERT INTO city (city_ID) VALUES (1);
INSERT INTO city (city_ID) VALUES (2);
INSERT INTO city (city_ID) VALUES (3);
INSERT INTO administrator (administrator_ID, city_ID) VALUES (1, 1);