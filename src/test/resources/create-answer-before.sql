delete from answer;
delete from question;
delete from form;

insert into form (id, active, name, user_id)
values (1, true, 'Тестовая форма', 1);

insert into question (id, question, form_id)
values (1, 'Тестовый вопрос', 1);

insert into answer (id, answer, author, date, question_id)
values (1, 'Тестовый ответ', 'Тестовый автор', CURRENT_DATE, 1);