create schema cgecommercedb collate utf8mb4_0900_ai_ci;

grant alter, alter routine, create, create routine, create temporary tables, create view, delete, drop, event, execute, index, insert, lock tables, references, select, show view, trigger, update on cgecommercedb.* to cgecommerceuser;

use cgecommercedb;

drop table if exists cgecommercedb.categories;

drop table if exists cgecommercedb.categories_seq;


# Hibernate:
drop table if exists categories;
# Hibernate:
create table categories
(
    category_id   bigint       not null auto_increment,
    category_name varchar(100) not null,
    primary key (category_id)
) engine = InnoDB;
# Hibernate:
alter table categories
    add constraint UK41g4n0emuvcm3qyf1f6cn43c0 unique (category_name)
