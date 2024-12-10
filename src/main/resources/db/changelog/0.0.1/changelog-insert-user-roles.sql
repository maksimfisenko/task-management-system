--liquibase formatted sql

--changeset maksimfisenko:insert-user-roles
--comment insert user roles
insert into identity.user_roles(authority)
    values ('ROLE_USER'), ('ROLE_ADMIN');
--rollback truncate table identity.user_roles
