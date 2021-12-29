create table answer (id bigint not null, answer varchar(255), author varchar(255), date datetime(6), question_id bigint, primary key (id)) engine=InnoDB;
create table form (id bigint not null, active bit not null, name varchar(255), user_id bigint, primary key (id)) engine=InnoDB;
create table form_questions (form_id bigint not null, questions_id bigint not null, primary key (form_id, questions_id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table question (id bigint not null, question varchar(255), form_id bigint, primary key (id)) engine=InnoDB;
create table user (id bigint not null, active bit not null, password varchar(255), username varchar(255), primary key (id)) engine=InnoDB;
create table user_role (user_id bigint not null, roles varchar(255)) engine=InnoDB;
alter table form_questions add constraint UK_7k839ap4840cwsmoxj6e8ecg0 unique (questions_id);
alter table answer add constraint FK8frr4bcabmmeyyu60qt7iiblo foreign key (question_id) references question (id);
alter table form add constraint FKsniuo4i0n35d0lw0pjlc2iqwe foreign key (user_id) references user (id);
alter table form_questions add constraint FKqgc13ev8prhcwgryo0i2bwnn5 foreign key (questions_id) references question (id);
alter table form_questions add constraint FK7w6vy0wl8o5hp9thqmriwehfi foreign key (form_id) references form (id);
alter table question add constraint FKfufwr8jclqwc3bw2d2tj4957f foreign key (form_id) references form (id);
alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id);

insert into user values (1, true, '123', 'root');
insert into user_role values (1, 'ADMIN');