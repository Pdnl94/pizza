insert into user (username, password, email, enabled, balance, role) values ('admin', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'test@example.com', true, 53400, 'ROLE_ADMIN');
insert into user (username, password, email, enabled, balance, role) values ('user', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'test@example.com', true, 46100, 'ROLE_USER');

insert into income (user_id, amount, description, comment) values (2, 1490, 'Pizza Margherita', 'no sajt');
insert into income (user_id, amount, description, comment) values (2, 1600, 'Proschiuto Cotto', 'extra sajt');
insert into income (user_id, amount, description, comment) values (2, 1700, 'Pizza Funghi', 'mushroom');
insert into income (user_id, amount, description, comment) values (2, 1720, 'Pizza Napoletano', 'spicy');
insert into income (user_id, amount, description, comment) values (2, 1790, 'Pizza Padrino', 'extra spicy');
insert into income (user_id, amount, description, comment) values (2, 1490, 'Pizza Gamberetti', 'extra sajt');
insert into income (user_id, amount, description, comment) values (2, 1490, 'Pizza Pepperoni', 'spicy');
insert into income (user_id, amount, description, comment) values (2, 1490, 'Pizza Spinacci', 'vegan');
insert into income (user_id, amount, description, comment) values (2, 1490, 'Quattro Stagioni', 'no sajt');
insert into income (user_id, amount, description, comment) values (2, 1990, 'Pizza di Parma', 'extra meat');
insert into income (user_id, amount, description, comment) values (2, 1290, 'Pizza Parmesan', 'extra sajt');
insert into income (user_id, amount, description, comment) values (2, 1290, 'Crema di Formaggio', 'extra sajt');
insert into income (user_id, amount, description, comment) values (2, 1490, 'Pizza Chili', 'extra spicy');
insert into income (user_id, amount, description, comment) values (2, 1390, 'Pizza Zucchini', 'vegan');
insert into income (user_id, amount, description, comment) values (2, 2190, 'Pizza al Salmone', 'fish');
insert into income (user_id, amount, description, comment) values (2, 1490, 'Cipolla e Fagioli', 'extra spicy');

insert into provider (name) values ('Mozarella');
insert into provider (name) values ('Pepperoni');
insert into provider (name) values ('Feta');
insert into provider (name) values ('Jalapeno');
insert into provider (name) values ('Ananasz');
insert into provider (name) values ('Tukortojas');
insert into provider (name) values ('Lilahagyma');

insert into item_category (name) values ('Csípős');
insert into item_category (name) values ('Laktózmentes');
insert into item_category (name) values ('Gluténmentes');
insert into item_category (name) values ('Vegán');
insert into item_category (name) values ('Fűszeres');

insert into item_category_providers (categories_id, providers_id) values (3, 1);
insert into item_category_providers (categories_id, providers_id) values (3, 2);
insert into item_category_providers (categories_id, providers_id) values (1, 3);
insert into item_category_providers (categories_id, providers_id) values (1, 4);
insert into item_category_providers (categories_id, providers_id) values (2, 3);
insert into item_category_providers (categories_id, providers_id) values (4, 6);
insert into item_category_providers (categories_id, providers_id) values (5, 7);


insert into outlay (price, name, provider_id, user_id) values (1490, 'Pizza Margherita', 1, 1);
insert into outlay (price, name, provider_id, user_id) values (1700, 'Pizza Funghi', 7, 1);
insert into outlay (price, name, provider_id, user_id) values (1490, 'Pizza Margherita', 1, 2);
insert into outlay (price, name, provider_id, user_id) values (1600, 'Proschiuto Cotto', 3, 2);
insert into outlay (price, name, provider_id, user_id) values (1720, 'Pizza Napoletano', 2, 2);
insert into outlay (price, name, provider_id, user_id) values (1790, 'Pizza Padrino', 3, 2);
insert into outlay (price, name, provider_id, user_id) values (1720, 'Pizza Napoletano', 2, 1);

insert into item (desc, category_id, outlay_id) values ('sajt', 2, 1);
insert into item (desc, category_id, outlay_id) values ('paradicsom', 1, 1);
insert into item (desc, category_id, outlay_id) values ('hagyma', 1, 1);
insert into item (desc, category_id, outlay_id) values ('jalapeno', 2, 2);
insert into item (desc, category_id, outlay_id) values ('bab', 2, 3);
insert into item (desc, category_id, outlay_id) values ('ketchup', 2, 3);