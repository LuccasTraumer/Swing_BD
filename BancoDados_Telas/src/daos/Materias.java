package daos;
import dbos.*;
import bd.*;
import java.sql.*;
import bd.BDSQLServer;
import bd.core.*;
public final class Materias {
	public static void incluir(Materia nova) throws Exception
	{
		if(nova == null)
			throw new Exception("Nova Materia est� Nula!");
		if(existe(nova.getCodigoMateria()))
			throw new Exception("Materia com Codigo ja existente!");
		try {
			String sql;
			sql = "INSERT INTO MATERIAS VALUES(?,?)";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setInt(1,nova.getCodigoMateria());
			BDSQLServer.COMANDO.setString(2,nova.getNomeMateria());
			BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
		}catch(SQLException erro)
		{
			throw new Exception("Erro ao Incluir Valor!");
		}
	}
	
	public static MeuResultSet mostrarMaterias() throws Exception
	{
		MeuResultSet resultado;
		try {
			String sql = "select * from MATERIAS";
			BDSQLServer.COMANDO.prepareStatement(sql);
			resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
		}catch(SQLException err) {
			throw new Exception("Erro ao Visualizar!");
		}
		
		return resultado;
	}
	public static boolean existe(int codigo) throws Exception
	{
            
		boolean retorno = false;
		if(codigo <= 0)
			throw new Exception("Codigo Invalido para Busc�!");
		try {
			String sql;
			sql = "SELECT * FROM MATERIAS WHERE CODIGOMATERIAS = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setInt(1,codigo);
			MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
			retorno = resultado.first();
			
		}catch(SQLException erro)
		{
			throw new Exception("Erro no Codigo Informado!");
		}
		return retorno;
	}
	public static void atualizar(Materia alt) throws Exception
	{
		if(alt == null)
			throw new Exception("Objeto Nulo!");
		if(!existe(alt.getCodigoMateria()))
			throw new Exception("N�o Existe materia com esse Codigo!");
		try {
			String sql;
			sql = "UPDATE MATERIAS SET NOME = ? WHERE CODIGOMATERIAS = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setString(1,alt.getNomeMateria());
			BDSQLServer.COMANDO.setInt(2,alt.getCodigoMateria());
			BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
		}catch(SQLException erro)
		{
			throw new Exception("Erro ao Atualizar Dados!");
		}
		
	}
	
	public static void excluir(int codigo) throws Exception
	{
		if(!existe(codigo))
			throw new Exception("Nao Existe Registro com esse Codigo!");
		try {
			String sql;
			sql = "DELETE FROM MATERIAS WHERE CODIGOMATERIAS = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setInt(1,codigo);
			BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
		}catch(SQLException erro)
		{
			throw new Exception("Erro ao Excluir dado!");
		}
	}
        
        public static boolean existe(String nomeMat) throws Exception
        {
            boolean retorno = false;
            if(nomeMat == null || nomeMat.equals(""))
                throw new Exception("Nome da Materia Invalido!");
            try{
                String sql;
                sql = "SELECT * FROM MATERIAS WHERE NOME = ?";
                BDSQLServer.COMANDO.prepareStatement(sql);
                BDSQLServer.COMANDO.setString(1,nomeMat);
                
                MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
                retorno = resultado.first();
            }catch(SQLException erro)
            {
                throw new Exception("Erro ao Encontar!");
            }
            
            return retorno;
        }
        public static void exclMateria(String nomeMaterias) throws Exception // Stored Procedure
        {
            if(nomeMaterias == null || nomeMaterias.equals("") || !existe(nomeMaterias))
                throw new Exception("Nome da Materia Invalido!");
            
            try{
                String sql;
                sql = "exlusaoMateria_sp ?";
                BDSQLServer.COMANDO.prepareStatement(sql);
                BDSQLServer.COMANDO.setString(1,nomeMaterias);
                
                BDSQLServer.COMANDO.executeUpdate();
                BDSQLServer.COMANDO.commit();
            }catch(SQLException erro)
            {
                throw new Exception("Erro ao Excluir no Banco");
            }
        }
        
        public static String showMaterias() throws Exception // View
        {
            String ret = "";
            try{
                String sql = "SELECT * FROM Materias_vw";
                BDSQLServer.COMANDO.prepareStatement(sql);
                MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
                
                while(resultado.next())
                    ret += resultado.getString("NOME") + ", ";
            }catch(SQLException erro)
            {
                throw new Exception("Erro ao Visualizar as Materias!");
            }
            return ret;
        }
        public static MeuResultSet materiasSemReprovacao() throws Exception
        {
            MeuResultSet resultado;
            try{
                String sql = "materiasSemReprovacao_sp";
                BDSQLServer.COMANDO.prepareStatement(sql);
                
                resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
                
            }catch(SQLException erro)
            {
                throw new Exception("Não Possivel Visualizar!");
            }
            return resultado;
        }
        public static MeuResultSet mediaCrescenteAlunos() throws Exception
        {
            MeuResultSet resultado;
            try{
                String sql = "mediaCrescenteAlunos_sp";
                BDSQLServer.COMANDO.prepareStatement(sql);
                resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
                
            }catch(SQLException erro){
            throw new Exception("Não Possivel Visualizar!");
            }
            return resultado;
        }
                
}
