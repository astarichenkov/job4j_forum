insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$RqWc3SniCG9sGmtWDVisz.uLEr4chhSTkvR1GrTwGG8HhdDKbAc6G',
        (select id from authorities where authority = 'ROLE_ADMIN'));
insert into users (username, enabled, password, authority_id)
values ('user', true, '123',
        (select id from authorities where authority = 'ROLE_USER'));