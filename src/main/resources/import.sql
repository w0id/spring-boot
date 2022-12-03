DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, cost real, name varchar(255));

INSERT INTO products(cost, name) VALUES(11.0, 'Товар#1');
INSERT INTO products(cost, name) VALUES(22.0, 'Товар#2');
INSERT INTO products(cost, name) VALUES(33.0, 'Товар#3');
INSERT INTO products(cost, name) VALUES(44.0, 'Товар#4');
INSERT INTO products(cost, name) VALUES(55.0, 'Товар#5');

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial PRIMARY KEY, first_name varchar(255), last_name varchar(255));

INSERT INTO users (first_name, last_name) VALUES('Василий', 'Иванов');
INSERT INTO users (first_name, last_name) VALUES('Петр', 'Сергеев');
INSERT INTO users (first_name, last_name) VALUES('Андрей', 'Александров');

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders (user_id bigserial,	product_id bigserial);

INSERT INTO orders (user_id, product_id) VALUES(1, 2);
INSERT INTO orders (user_id, product_id) VALUES(1, 3);
INSERT INTO orders (user_id, product_id) VALUES(2, 1);
INSERT INTO orders (user_id, product_id) VALUES(3, 5);
INSERT INTO orders (user_id, product_id) VALUES(3, 1);