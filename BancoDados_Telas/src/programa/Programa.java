package programa;
import bd.*;
import daos.*;
import dbos.*;
public class Programa {
    public static void main(String[] args) throws Exception
    {
        //Alunos.incluir(new Aluno(19372,"Lucas","lucas@gmail.com"));
        //System.out.println(Alunos.showAlunos());//
        //Materias.incluir(new Materia(2,"Desenvolvimento para Internet 2"));
        //Fezs.incluir(new Fez(19372,2,9.0f,65));
        System.out.println(Alunos.showAlunos());
    }
}
