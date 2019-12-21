-- CRIANDO AS TABELAS
create table ALUNOS(
RA int primary key,
NOME varchar(100) not null,
EMAIL varchar(150) not null)


create table MATERIAS(
codigoMaterias int primary key,
NOME varchar(150) not null)


create table FEZ(
RA int not null,
codigoMaterias int not null,
NOTA float not null,
frequencia int not null
constraint fkRA foreign key(RA) references ALUNOS(RA),
constraint fkcodMaterias foreign key(codigoMaterias) references MATERIAS(codigoMaterias))

select * from FEZ

update FEZ SET frequencia = 80, nota = 7.5 where RA = 19372 and codigoMaterias = 2
insert into FEZ values(19372,2,8.0,75)
-- RELATORIO

--alunos que tiveram 0% de frequencia ALUNOS
alter proc frequenciaAlunos_sp
as
select a.nome,avg(f.frequencia) as 'Frequencia'from 
ALUNOS a, FEZ f
where
a.RA = f.RA and
f.frequencia = 0
GROUP BY a.NOME

--nome das materias sem reprovacao materias MATERIAS
alter proc materiasSemReprovacao_sp
as
select m.nome from MATERIAS m 
inner join FEZ f on m.codigoMaterias = f.codigoMaterias
where 
f.NOTA > 5 

insert into FEZ values(19771,3,3,50)
select * from FEZ
--nome das materias ordenado de forma crescente pela media dos alunos MATERIAS
mediaAlunosPorMateria_sp
create proc mediaCrescenteAlunos_sp
as
select m.nome, avg(f.nota)as 'MediaAlunos' from MATERIAS m
inner join FEZ f on m.codigoMaterias = f.codigoMaterias
group by m.NOME
order by m.NOME asc

drop proc mediaCrescenteAlunos_sp

--nome dos alunos ordenados crescente pela média de suas matérias ALUNOS
alter proc mediaAlunosPorMateria_sp
as
select a.nome as 'NomeAluno',m.nome as 'NomeMateria',avg(f.NOTA)as 'Media' from ALUNOS a
inner join FEZ f on a.RA = f.RA 
inner join MATERIAS m on m.codigoMaterias = f.codigoMaterias
group by a.NOME,m.NOME
order by avg(f.NOTA) asc

