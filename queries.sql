
drop table Product cascade constraint;

create table Product(
       id number,
       name varchar2(20),
       price number(7,2),
       category varchar2(20),
       ManufactureDate date,
       primary key(id)
);

drop sequence pro_id_seq;

create sequence pro_id_seq start with 1000;

select * from Product;

delete from product where id > 1001;




