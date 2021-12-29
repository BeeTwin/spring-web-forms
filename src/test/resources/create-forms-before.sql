delete from answer;
delete from question;
delete from form;

insert into form (id, active, name, user_id)
values (1, true, 'Тестовая форма', 1);