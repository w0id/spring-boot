CREATE TABLE users (
                       id bigserial NOT NULL,
                       username varchar(30) NOT NULL,
                       "password" varchar(80) NOT NULL,
                       email varchar(50) NULL,
                       CONSTRAINT users_email_key UNIQUE (email),
                       CONSTRAINT users_pkey PRIMARY KEY (id),
                       CONSTRAINT users_username_key UNIQUE (username)
);

CREATE TABLE roles (
                       id serial4 NOT NULL,
                       "name" varchar(50) NOT NULL,
                       CONSTRAINT roles_pkey PRIMARY KEY (id)
);

CREATE TABLE users_roles (
    user_id             bigint not null,
    role_id             int not null,
    PRIMARY KEY (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
)

INSERT INTO roles (name)
    values
    ('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MANAGER');

INSERT INTO users (username, password, email)
values
    ('user', '123321', 'user@mail.ru'),
    ('admin', 'Awds#w12', 'admin@mail.ru'),
    ('manager', '!Q@W3e4r', 'manager@mail.ru');

INSERT INTO users_roles (user_id, role_id)
values
    (1, 1),
    (2, 2),
    (3, 3);