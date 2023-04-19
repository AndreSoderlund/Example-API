CREATE TABLE persons
(
    id     INTEGER        NOT NULL AUTO_INCREMENT,
    name   VARCHAR(30) NOT NULL,
    email  VARCHAR(50) DEFAULT '',
    age    INTEGER     NOT NULL,
    gender VARCHAR(30) NOT NULL DEFAULT 'MALE',
    CONSTRAINT pk_persons PRIMARY KEY (id)
);

CREATE SEQUENCE persons_seq START WITH 5;

INSERT INTO persons(id, name, email, age, gender)
VALUES (1, 'André', 'andre.soderlund@b3.se', '28', 'MALE');

INSERT INTO persons(id, name, email, age, gender)
VALUES (2, 'Eddie', 'eddie@gmail.com', '39', 'MALE');

INSERT INTO persons(id, name, age, gender)
VALUES (3, 'Ronja', '37', 'FEMALE');

INSERT INTO persons(id, name, age, gender)
VALUES (4, 'Rasmus', '26', 'NON_BINARY');