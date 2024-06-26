DROP TABLE IF EXISTS TBL_TRIPS;
DROP TABLE IF EXISTS TBL_RESERVATIONS;

CREATE TABLE TBL_TRIPS (
  id INT AUTO_INCREMENT PRIMARY KEY,
  bus INT NOT NULL,
  from_city VARCHAR(32) NOT NULL,
  to_city VARCHAR(32) NOT NULL,
  date_trip VARCHAR(32) NOT NULL,
  time_trip VARCHAR(32) NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  available_seats INT NOT NULL
);

CREATE TABLE TBL_RESERVATIONS (
  id INT AUTO_INCREMENT PRIMARY KEY,
  token VARCHAR(32) NOT NULL,
  from_city VARCHAR(32) NOT NULL,
  to_city VARCHAR(32) NOT NULL,
  date_trip VARCHAR(32) NOT NULL,
  time_trip VARCHAR(32) NOT NULL,
  first_name VARCHAR(32) NOT NULL,
  last_name VARCHAR(32) NOT NULL,
  email VARCHAR(64) NOT NULL
);

