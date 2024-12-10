--liquibase formatted sql

--changeset maksimfisenko:create-identity-schema
--comment create identity schema
create schema identity;
--rollback drop schema identity;

--changeset maksimfisenko:create-identity.user_accounts-table
--comment create identity.user_accounts table
create table identity.user_accounts(
    id serial primary key,
    username varchar(32) unique not null,
    password varchar(128) not null
);
--rollback drop table identity.user_accounts;

--changeset maksimfisenko:create-identity.user_roles-table
--comment create identity.user_roles table
create table identity.user_roles(
    id serial primary key,
    authority varchar(32) unique not null
);
--rollback drop table identity.user_roles;

--changeset maksimfisenko:create-identity.user_accounts_roles-table
--comment create identity.user_accounts_roles table
create table identity.user_accounts_roles(
    user_account_id integer not null,
    user_role_id integer not null
);
--rollback drop table identity.user_accounts_roles;

--changeset maksimfisenko:add-identity.user_accounts_roles-table-constraints
--comment add identity.user_accounts_roles table constraints
alter table identity.user_accounts_roles
    add constraint user_accounts_roles_FK01
        foreign key (user_account_id) references identity.user_accounts (id);

alter table identity.user_accounts_roles
    add constraint user_accounts_roles_FK02
        foreign key (user_role_id) references identity.user_roles (id);

alter table identity.user_accounts_roles
    add constraint user_accounts_roles_UQ01
        unique (user_account_id, user_role_id);

--rollback alter table identity.user_accounts_roles drop constraint user_accounts_roles_FK01
--rollback alter table identity.user_accounts_roles drop constraint user_accounts_roles_FK02
--rollback alter table identity.user_accounts_roles drop constraint user_accounts_roles_UQ01