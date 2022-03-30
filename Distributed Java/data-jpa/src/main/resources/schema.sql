create table catalog (
    id int primary key,
    name varchar(50) not null,
    description varchar(200) not null,
    price double not null,
    stock int not null
);