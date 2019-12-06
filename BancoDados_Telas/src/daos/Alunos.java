package daos;
import dbos.*;
import bd.*;
import bd.core.*;
import java.sql.*;
import bd.BDSQLServer;
public final class Alunos {
	
	public static void incluir(Aluno novo) throws Exception
	{
		if(novo == null)
			throw new Exception("Novo Valor Invalido para Inserï¿½ï¿½o!");
		if(existe(novo.getRa()))
			throw new Exception("Ja existe um Aluno com este RA");
		try {
			String sql;
			sql = "INSERT INTO ALUNOS VALUES(?,?,?)";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setInt(1,novo.getRa());
			BDSQLServer.COMANDO.setString(2,novo.getNome());
			BDSQLServer.COMANDO.setString(3,novo.getEmail());
			
			BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
		}catch(SQLException erro)
		{
			throw new Exception("Erro ao Incluir novo Aluno!llll");
		}
	}
	
	public static MeuResultSet mostrarAlunos() throws Exception
	{
		MeuResultSet resultado;
		try {
			String sql = "select * from Alunos";
			BDSQLServer.COMANDO.prepareStatement(sql);
			resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
		}catch(SQLException err)
		{
			throw new Exception("Não Possivel exibir!");
			
		}
		return resultado;
	}
	public static boolean existe(int ra) throws Exception
	{
		//if(ra <= 0)
		//	throw new Exception("RA Invalido para verificaï¿½ï¿½o!");
		boolean retorno = false;
		try {
			String sql;
			sql = "SELECT * FROM ALUNOS WHERE RA = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setInt(1,ra);
			MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
			retorno = resultado.first();
			
		}catch(SQLException erro)
		{
			throw new Exception("Erro ao Procurar RA!");
		}
		return retorno;
	}
	public static void atualizar(Aluno alt) throws Exception
	{
		if(alt == null)
			throw new Exception("Objeto Invalido para Alteraï¿½ï¿½o!");
		if(!existe(alt.getRa()))
			throw new Exception("RA nï¿½o Armazenado na BASE de DADOS");
		try {
			String sql;
			sql = "UPDATE ALUNOS SET NOME = ?, EMAIL = ? WHERE RA = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setString(1,alt.getNome());
			BDSQLServer.COMANDO.setString(2,alt.getEmail());
			BDSQLServer.COMANDO.setInt(3,alt.getRa());
			BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
		}catch(SQLException erro)
		{
			throw new Exception("Erro ao Atualizar dados!");
		}
	}
	public static void excluir(int ra) throws Exception
	{
		if(!existe(ra))
			throw new Exception("Codigo nï¿½o armazenado!");
		try {
			String sql;
			sql = "DELETE FROM ALUNOS WHERE RA = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setInt(1,ra);
			BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
			
		}catch(SQLException erro)
		{
			throw new Exception("Nï¿½o foi Possivel Exclui o dado!");
		}
	}
		public static MeuResultSet notaAcimaDe(float nota) throws Exception 
		{
			if(nota < 0 || nota > 10)
				throw new Exception("Nota Invalida!");
			MeuResultSet resultado;
			try {
				String sql = "notaAcima_sp ?";
				BDSQLServer.COMANDO.prepareStatement(sql);
				BDSQLServer.COMANDO.setFloat(1,nota);
				resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
			}catch(SQLException err) {
				throw new Exception("Erro ao Pegar dados");
			}
			return resultado;
		}
		public static MeuResultSet frequenciaAcima(int frequencia) throws Exception
		{
			if(frequencia < 0 || frequencia > 100)
				throw new Exception("Frequencia Invalida!");
			MeuResultSet resultado;
			try {
				String sql = "frequenciaAcima_sp ?";
				BDSQLServer.COMANDO.prepareStatement(sql);
				BDSQLServer.COMANDO.setInt(1,frequencia);
				resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
			}catch(SQLException err)
			{
				throw new Exception("Erro ao Visualizar!");
			}
			
			return resultado;
		}
        public static void excAluno(int ra) throws Exception // Stored Procedure
        {
            if(ra <= 0)
                throw new Exception("RA invalido!");
            try{
                String sql;
                sql = "exclusaoAluno_sp ?";
                BDSQLServer.COMANDO.prepareStatement(sql);
                BDSQLServer.COMANDO.setInt(1, ra);
                
                BDSQLServer.COMANDO.executeUpdate();
                BDSQLServer.COMANDO.commit();
            }catch(SQLException erro)
            {
                throw new Exception("Erro ao Excluir!");
            }
        }
	public static String showAlunos() throws Exception // View
        {
            String ret = "";
            try{
                String sql = "SELECT * FROM ALUNOS_VW";
                BDSQLServer.COMANDO.prepareStatement(sql);
                MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
                while(resultado.next())
                    ret += "RA: "+resultado.getInt("RA") +", Nome: "+resultado.getString("NOME")+"\n";
            }catch(SQLException erro)
            {
                throw new Exception("Erro ao Visualizar a Tabela");
            }
            return ret;
        }
        public static String mediaAlunos() throws Exception // Group by
        {
            String ret ="";

            try{
                String sql = "SELECT A.NOME, AVG(F.NOTA) AS 'MEDIA' FROM ALUNOS A, FEZ F WHERE A.RA = F.RA GROUP BY A.NOME";
                BDSQLServer.COMANDO.prepareStatement(sql);
                
                MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
                while(resultado.next())
                    ret += "Nome: "+resultado.getString("NOME") + ", Media: "+ resultado.getString("MEDIA");
                
            }catch(SQLException erro)
            {
                throw new Exception("NÃ£o foi Possivel Verificar");
            }
            
            return ret;
        }
        
        public static String frenquenciaAlunos() throws Exception
        {
            String ret = "";
            try{
                String sql = "select a.nome as 'NomeAluno',m.NOME as 'NomeMateria', "
                        + "avg(f.frequencia) as 'FREQ' from ALUNOS a  " +
                        "inner join FEZ f on a.RA = f.RA " +
                        "inner join MATERIAS m on m.codigoMaterias = f.codigoMaterias " +
                        "where " +
                        "m.codigoMaterias = f.codigoMaterias and " +
                        "a.RA = f.RA " +
                        "group by a.NOME,m.NOME";
                
                BDSQLServer.COMANDO.prepareStatement(sql);
                MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
                while(resultado.next())
                    ret += "Aluno: "+resultado.getString("NomeAluno") +", Materia: " + resultado.getString("NomeMateria")+", Frequencia: "+ resultado.getString("FREQ")+"%"+"\n";
                
            }catch(SQLException erro)
            {
                throw new Exception("Erro ao Visualizar!");
            }
            return ret;
        }
        
        public static String frequenciaOrdemCrescente() throws Exception
        {
            String ret = "";
            try{
                String sql = "ordemCrescenteFreq_sp";
                BDSQLServer.COMANDO.prepareStatement(sql);
                MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
                while(resultado.next())
                    ret += "Aluno: "+ resultado.getString("NOME") + ", Frequencia: "+resultado.getString("FREQ");
            }catch(SQLException erro)
            {
                throw new Exception("Erro ao Visualizar Tabela!");
            }
            return ret;
        }
        
        public static String alunosFrequencia() throws Exception
        {
            String ret= "";
        
            try{
            String sql = "frequenciaAlunos_sp";
            BDSQLServer.COMANDO.prepareStatement(sql);
            MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
            while(resultado.next())
                ret += "Aluno: "+ resultado.getString("NOME")+", Frequencia: "+resultado.getInt("Frequencia")+"\n ";
        }
            catch(SQLException erro)
        {
            throw new Exception("Erro ao Visualizar!");
            
        }
            return ret;
        }
        public static String mediaAlunosPorMateria() throws Exception
        {
            String ret = "";
            try{
                String sql = "mediaAlunosPorMateria_sp";
                BDSQLServer.COMANDO.prepareStatement(sql);
                MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
                while(resultado.next())
                    ret += "Nome Aluno: "+ resultado.getString("NomeAluno") + ", Nome Materia: "+resultado.getString("NomeMateria")+ ", Media: "+resultado.getFloat("Media")+"\n";
            }catch(SQLException erro)
            {
                throw new Exception("Erro ao Visualizar!");
            }
            return ret;
        }
        
        public static String alunosAcimaDe(float nota) throws Exception
        {
            if(nota < 0 || nota > 10)
                throw new Exception("Nota Invalida!");
            String ret = "";
            try{
                String sql = "notaAcima_sp ?";
                BDSQLServer.COMANDO.prepareStatement(sql);
                BDSQLServer.COMANDO.setFloat(1, nota);
                MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
                //while(resultado.next())
                ret+= resultado; 
            }catch(SQLException erro)
            {
                throw new Exception("Erro ao Visualizar!");
            }
            return ret;
        }
        
}
