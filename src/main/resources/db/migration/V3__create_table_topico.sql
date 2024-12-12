CREATE TABLE usuario(
id bigint not null auto_increment,
titulo varchar(50) not null,
mensagem varchar(300) not null,
data_criacao datetime not null,
status varchar(50) not null,
curso_id bigint not null,
autor_id bigint not null,
primary ky(id)
foreing key(curso_id) references curso(id),
foreing key(autor_id) references autor(id)

);