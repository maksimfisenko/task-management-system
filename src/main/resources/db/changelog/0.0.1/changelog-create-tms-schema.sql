--liquibase formatted sql

--changeset maksimfisenko:create-tms-schema
--comment create tms schema
create schema tms;
--rollback drop schema tms;