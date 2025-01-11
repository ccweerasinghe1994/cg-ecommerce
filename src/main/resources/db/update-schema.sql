create schema cgecommercedb collate utf8mb4_0900_ai_ci;

grant alter, alter routine, create, create routine, create temporary tables, create view, delete, drop, event, execute, index, insert, lock tables, references, select, show view, trigger, update on cgecommercedb.* to cgecommerceuser;

use cgecommercedb;

drop table if exists cgecommercedb.categories;

create table cgecommercedb.categories
(
    category_id   bigint       not null primary key,
    category_name varchar(255) null
);


