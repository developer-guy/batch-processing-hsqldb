SET AUTOCOMMIT TRUE;
DROP TABLE people
IF EXISTS;
CREATE TABLE people (
  person_id  BIGINT IDENTITY NOT NULL PRIMARY KEY,
  first_name VARCHAR(20),
  last_name  VARCHAR(20)
);

INSERT INTO people (person_id, first_name, last_name) VALUES (1, 'Batuhan', 'Apaydın');