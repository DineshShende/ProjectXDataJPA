drop table customer_quick_register_entity if exists;

create table customer_quick_register_entity (customer_id bigint generated by default as identity (start with 1), email varchar(255), email_hash bigint, first_name varchar(255), last_name varchar(255), mobile bigint, mobile_pin integer, pin integer, status varchar(255), primary key (customer_id));

alter table customer_quick_register_entity add constraint UK_489ghacvvidt74n66lx26o2j  unique (email);

alter table customer_quick_register_entity add constraint UK_q9lcp8rk0ctsnf48nuejvtmus  unique (mobile);