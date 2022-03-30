create table Squirrel (
      squirrel_id int primary key,
      species varchar(200) not null,
      common_name varchar(200) not null,
      habitat varchar(200) not null,
      image_file_name varchar(200),
      ranking int not null
);

create table Location (
    location_id int primary key,
    name varchar(200) not null,
    country varchar(50) not null
);

create table Sighting (
    sighting_id identity,
    sq_id int not null,
    spotter_name varchar(200) not null,
    loc_id int not null,
    count int not null default 0,
    spotted_at timestamp not null
);

alter table Sighting
    add foreign key (sq_id) references Squirrel(squirrel_id);

alter table Sighting
    add foreign key (loc_id) references Location(location_id);