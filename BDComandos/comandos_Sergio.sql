/*
ARQUIVOS COM OS COMANDOS NECESSARIOS DO PROF. SERGIO DO TRABALHO.


*/



-- Stored PROCEDURES 
create procedure exclusaoAluno_sp -- SENDO UTILIZADO CLASSE ALUNODAO
@ra int = null
as 
if(@ra is null)
	print'Ra Invalido pra Exclusão!'
else
begin 
	delete from ALUNOS where RA = @ra

end
select * from ALUNOS
drop proc exclusaoAluno_sp


-- Stored PROCEDURES

create proc exlusaoMateria_sp -- SENDO UTILIZADO CLASSE MATERIADAO
@nome varchar(100) = null
as 
if(@nome is null)
	print'Nome Invalido para Exclusao!'
else
begin
	delete from MATERIAS where NOME = @nome
end

exlusaoMateria_sp @nome = 'TP2'
insert into MATERIAS values(1,'TP2')

select * from MATERIAS

-- 3 Procedure ** MOSTRA OS ALUNOS PELA FREQUENCIA CRESCENTE
alter proc ordemCrescenteFreq_sp -- SENDO ULTILIZADO CLASSE ALUNOSDAO
as
select a.NOME, AVG(f.frequencia) as 'MediaFreq' from FEZ f inner join ALUNOS a
on f.RA = a.RA
group by a.nome
order by avg(f.frequencia) desc

drop proc ordemCrescenteFreq_sp
-- 1 criando trigger ** CASO INSIRA ALGUM ALUNO A TRIGGER É DISPARADA == FEITO

create trigger inseriuAluno_tg on ALUNOS -- SENDO ULTILIZADO 
for INSERT
as
print'Aluno INSERIDO'
-- 2 Trigger ** CASO DELETE ALGUM ALUNO A TRIGGER É DISPARADA == FEITO
create trigger deletarAluno_tg on ALUNOS -- SENDO ULTILIZADO 
for DELETE
as
print 'ALUNO DELETADO'

-- 1 Cursor ** ALUNOS COM MENOR FREQUENCIA ATÉ MAIOR 
create proc freqCrescente_sp
as


select * from FEZ order by frequencia desc

-- 2 Cursor ** ALUNOS ACIMA DE UMA NOTA
create proc notaAcima_sp  -- NÃO CONSEGUI IMPLEMENTAR POIS NÃO SEI MANUSEAR CURSOR AINDA
@nota float = null
as
if(@nota < 0 or @nota > 11)
print'Nota Invalida!'
else
	begin
	declare @nomeAluno varchar(100)
	declare @notaAluno float
	declare notaAci scroll cursor for
	select a.nome, f.nota from ALUNOS a 
	inner join FEZ f on a.RA = f.RA
	where 
	f.NOTA > @nota
	open notaAci
	fetch next from notaAci into @nomeAluno,@notaAluno
	while @@FETCH_STATUS = 0
	begin
		print'Aluno: '+ @nomeAluno + ' Nota: '+ cast(@notaAluno as varchar(10))
		fetch next from notaAci into @nomeAluno,@notaAluno
	end 
	close notaAci
	deallocate notaAci
end

notaAcima_sp 5

select * from FEZ
-- 1 Tabela Temporaria ** Armazenas os Dados Anteriores


-- 2 Tabela Temporaria ** 


-- 1 Group by ** REUNIR MEDIA DE CADA ALUNO

select a.nome,AVG(f.NOTA) as 'Media' from ALUNOS a,FEZ f 
where 
a.RA = f.RA
group by a.NOME


insert into FEZ values(1,1,10.0,100)
insert into FEZ values(1,1,8.0,100)
-- 2 Group by ** FREQUENCIA DOS ALUNOS nas Materias

select a.nome as 'NomeAluno',m.NOME as 'NomeMateria', avg(f.frequencia) as 'FREQ' from ALUNOS a 
inner join FEZ f on a.RA = f.RA
inner join MATERIAS m on m.codigoMaterias = f.codigoMaterias
where 
m.codigoMaterias = f.codigoMaterias and
a.RA = f.RA
group by a.NOME,m.NOME


--1 view ** VISUALIZA TODAS AS MATERIAS == FEITO
create view Materias_vw -- UTILIZADA NA CLASSE MATERIA
as 
select * from MATERIAS

select * from Materias_vw
--2 view ** VISUALIZA TODOS OS ALUNOS == FEITO

create view Alunos_vw -- UTILIZADA NA CLASSE ALUNO
as 
select * from ALUNOS

select * from FEZ


--1 defalut



--2 defalut



-- 1 rules ** REGRA DO EMAIL DO ALUNO


-- 2 rules ** FREQUENCIA DOS ALUNOS
create rule frequenciaNegativa_rule -- UTILIZADA NO CAMPO FREQUENCIA
as 
@freq >= 0 and @freq <= 100

SP_BINDRULE frequenciaNegativa_rule,'FEZ.FREQUENCIA'
-- 3 rules ** CODIGO PRIMARIO DAS TABELAS
create rule codigoValido_rule 
as
@codigo > 0

SP_BINDRULE codigoValido_rule,'ALUNOS.RA'
SP_BINDRULE codigoValido_rule,'MATERIAS.codigoMaterias'

-- 4 rules ** Para a Nota dos Alunos
create rule notaValida_rule -- UTILIZADA 
as 
@nota >= 0 and @nota <= 10

SP_BINDRULE notaValida_rule,'FEZ.NOTA'