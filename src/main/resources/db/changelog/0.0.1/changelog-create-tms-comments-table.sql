--liquibase formatted sql

--changeset maksimfisenko:create-tms.comments-table
--comment create tms.comments table
create table tms.comments (
    id serial primary key,
    message varchar(512) not null,
    task_id integer not null,
    author_id integer not null,
    created_timestamp timestamp not null,
    modified_timestamp timestamp not null
);
--rollback drop table tms.comments

--changeset maksimfisenko:add-tms.comments-table-constraints
--comment add tms.comments table constraints
alter table tms.comments
    add constraint comments_FK01
        foreign key (author_id) references tms.user_profiles (id);
alter table tms.comments
    add constraint comments_FK02
        foreign key (task_id) references tms.tasks (id);
--alter table tms.tasks drop constraint comments_FK01;
--alter table tms.tasks drop constraint comments_FK02;