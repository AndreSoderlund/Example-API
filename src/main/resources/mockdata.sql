CREATE TABLE persons
(
    id     INTEGER        NOT NULL AUTO_INCREMENT,
    name   VARCHAR(30)    NOT NULL,
    email  VARCHAR(50)             DEFAULT '',
    age    INTEGER        NOT NULL,
    salary DECIMAL(10, 2) NOT NULL,
    gender VARCHAR(30)    NOT NULL DEFAULT 'MALE',
    role   VARCHAR(30)    NOT NULL DEFAULT 'TESTER',
    CONSTRAINT pk_persons PRIMARY KEY (id)
);

CREATE SEQUENCE persons_seq START WITH 7;

INSERT INTO persons(id, name, email, age, salary, gender, role)
VALUES (1, 'André', 'andre.soderlund@b3.se', '28', '35000', 'MALE', 'PRODUCT_OWNER');

INSERT INTO persons(id, name, email, age, salary, gender, role)
VALUES (2, 'Eddie', 'eddie@gmail.com', '39', '35000', 'MALE', 'PRODUCT_OWNER');

INSERT INTO persons(id, name, age, salary, gender, role)
VALUES (3, 'Ronja', '37', '30000', 'FEMALE', 'DEVELOPER');

INSERT INTO persons(id, name, age, salary, gender, role)
VALUES (4, 'Rasmus', '26', '40000', 'NON_BINARY', 'DEVELOPER');

INSERT INTO persons(id, name, email, age, salary, gender, role)
VALUES (5, 'Karl', 'karl@gmail.com', '45', '45000', 'MALE', 'TESTER');

INSERT INTO persons(id, name, email, age, salary, gender, role)
VALUES (6, 'Anders', 'anders@gmail.com', '50', '60000', 'MALE', 'SCRUM_MASTER');