DROP TABLE IF EXISTS Student;

CREATE TABLE Student
(
    id   bigserial,
    name varchar(255),
    mark numeric(2, 1),
    primary key (id)
);

INSERT INTO Student (name, mark)
VALUES ('Bob', 5),
       ('Mark', 4),
       ('Ann', 4.5);