--insert into users (id, name, password) values (1, 'henryadmin1', 'henry123');
--insert into users (id, name, password) values (2, 'olivertrainer1', 'oliver123');
--insert into users (id, name, password) values (3, 'jamestrainer2', 'james123');
--insert into users (id, name, password) values (4, 'noahuser1', 'noah123');
--insert into users (id, name, password) values (5, 'elijahuser2', 'elijah123');
--insert into users (id, name, password) values (6, 'lucasuser3', 'lucas123');

create table my_user(
	user_id int primary key auto_increment,
    user_name varchar(25),
    password varchar(50)
);

insert into my_user (user_id, user_name, password) values (1, 'henryadmin1', 'henry123');
insert into my_user (user_id, user_name, password) values (2, 'olivertrainer1', 'oliver123');
insert into my_user (user_id, user_name, password) values (3, 'jamestrainer2', 'james123');
insert into my_user (user_id, user_name, password) values (4, 'noahuser1', 'noah123');
insert into my_user (user_id, user_name, password) values (5, 'elijahuser2', 'elijah123');
insert into my_user (user_id, user_name, password) values (6, 'lucasuser3', 'lucas123');