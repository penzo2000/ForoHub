
create table topics(

id bigint not null auto_increment,
titulo varchar(100)not null unique,
mensaje varchar(700)not null unique,
fecha datetime not null,
status varchar(100)not null,
idUsuario bigint not null,
curso varchar(100)not null ,


primary key(id)



);