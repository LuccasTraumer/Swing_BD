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



-- RELATORIO

--alunos que tiveram 0% de frequencia ALUNOS
create proc frequenciaAlunos_sp
as
select a.nome,avg(f.frequencia) as 'Frequencia'from 
ALUNOS a, FEZ f
where
a.RA = f.RA
GROUP BY a.NOME
order by avg(f.frequencia) asc
--nome das materias sem reprovacao materias MATERIAS
alter proc materiasSemReprovacao_sp
as
select m.nome, avg(f.NOTA) as 'Media'from MATERIAS m 
inner join FEZ f on m.codigoMaterias = f.codigoMaterias
where 
f.NOTA > 5
group by m.NOME
order by avg(f.NOTA) asc
select * from MATERIAS
--nome das materias ordenado de forma crescente pela media dos alunos MATERIAS

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

