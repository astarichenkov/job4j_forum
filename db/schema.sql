create table users
(
    id       serial primary key,
    username varchar(2000),
    password varchar(2000)
);

create table posts
(
    id          serial primary key,
    name        varchar(2000),
    description text,
    user_id     int references users(id),
    created     timestamp without time zone not null default now()
);

create table comments
(
    id      serial primary key,
    text    varchar(2000),
    created timestamp without time zone not null default now()
);

create table posts_comments
(
    posts_id int references posts(id),
    comments_id int references comments(id)
);

