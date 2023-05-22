alter table medicos
    add column telefone varchar(20);

update medicos set telefone = '11999999999' where telefone is null;

alter table medicos
    alter column telefone set not null;