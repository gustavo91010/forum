CREATE TABLE resposta(
id bigint not null auto_increment,
mensagem varchar(300) not null,
data_criacao datetime not null,
topico_id bigint not null,
autor_id bigint not null,
solucao int(1) not null,
primary ky(id),
foreing key(topico_id) references topico(id),
foreing key(autor_id) references usuario(id)

);