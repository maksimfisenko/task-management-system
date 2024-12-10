--liquibase formatted sql

--changeset maksimfisenko:create-tms.user_profiles-table
--comment create tms.user_profiles table
create table tms.user_profiles (
    id integer primary key,
    username varchar(32) not null unique
);
--rollback drop table tms.user_profiles

--changeset maksimfisenko:add-tms.user_profiles-table-constraints
--comment add tms.user_profiles table constraints
alter table tms.user_profiles
    add constraint user_profiles_FK01
        foreign key (id) references identity.user_accounts (id);
alter table tms.user_profiles
    add constraint user_profiles_FK02
        foreign key (username) references identity.user_accounts (username);
--alter table tms.user_profiles drop constraint user_profiles_FK01;
--alter table tms.user_profiles drop constraint user_profiles_FK02;
