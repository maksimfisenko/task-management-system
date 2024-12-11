--liquibase formatted sql

--changeset maksimfisenko:create-tms.tasks-table
--comment create tms.tasks table
create table tms.tasks (
    id serial primary key,
    title varchar(32) not null,
    description varchar(256),
    status varchar(32) not null,
    priority varchar(32) not null,
    author_id integer not null,
    executor_id integer,
    created_timestamp timestamp not null
);
--rollback drop table tms.tasks

--changeset maksimfisenko:add-tms.tasks-table-constraints
--comment add tms.tasks table constraints
alter table tms.tasks
    add constraint tasks_FK01
        foreign key (author_id) references tms.user_profiles (id);
alter table tms.tasks
    add constraint tasks_FK02
        foreign key (executor_id) references tms.user_profiles (id);
--alter table tms.tasks drop constraint tasks_FK01;
--alter table tms.tasks drop constraint tasks_FK02;

--changeset maksimfisenko:add-tms.tasks-table-column-modified_timestamp
--comment add tms.tasks table column modified_timestamp
alter table tms.tasks
    add column modified_timestamp timestamp;

update tms.tasks
set modified_timestamp = created_timestamp
where modified_timestamp is null;

alter table tms.tasks
    alter column modified_timestamp set not null;

--alter table tms.tasks drop column modified_timestamp;
