delete from answer;
delete from question;
delete from form;
delete from user_role;
delete from user;

insert into user(id, active, password, username)
values (1, true, '123', 'test_user');
insert into user_role (user_id, roles)
values (1, 'USER');

