package daos;
import dbos.*;
import bd.*;
import bd.core.*;
import java.sql.*;
import bd.BDSQLServer;
public final class Fezs {
	public static boolean existe(int ra) throws Exception
	{
		if(ra <= 0)
			throw new Exception("RA invalido para Busca!");
		boolean retorno = false;
		try {
			String sql;
			sql = "SELECT * FROM FEZ WHERE RA = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setInt(1,ra);
			
			MeuResultSet resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
			retorno = resultado.first();
		}catch(SQLException erro)
		{
			throw new Exception("N�o Possivel acessar dado!");
		}
		return retorno;
	}
	public static MeuResultSet mostraFez() throws Exception
	{
		MeuResultSet resultado;
		try {
			
			String sql = "SELECT * FROM FEZ";
			BDSQLServer.COMANDO.prepareStatement(sql);
			resultado = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
		}catch(SQLException err) {
			throw new Exception("Erro ao Visualizar!");
		}
		return resultado;
	}
	public static void incluir(Fez novo) throws Exception
	{
		if(novo == null)
			throw new Exception("Novo Objeto Invalido para Inser��o!");
		if(existe(novo.getRa()))
			throw new Exception("Ja Existe um Aluno com este RA!");
		try {
			String sql;
			sql = "INSERT INTO FEZ VALUES(?,?,?,?)";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setInt(1,novo.getRa());
			BDSQLServer.COMANDO.setInt(2,novo.getCodigoMateria());
			BDSQLServer.COMANDO.setFloat(3,novo.getNota());
			BDSQLServer.COMANDO.setInt(4,novo.getFrequencia());
			
			BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
		}catch(SQLException erro)
		{
			throw new Exception("Erro ao Incluir Dado!");
		}
	}
	
	public static void excluirAluno(int ra) throws Exception
	{
            
		if(!existe(ra))
			throw new Exception("RA invalido para Exclus�o!");
		
		try {
			String sql;
			sql = "DELETE FROM FEZ WHERE RA = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setInt(1,ra);
			BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
			
		}catch(SQLException erro)
		{
			throw new Exception("Erro ao Excluir Dado!");
		}
	}
	public static void excluir(int ra,int codMat) throws Exception{
		if(ra < 9999 || codMat < 0)
			throw new Exception("RA ou Codigo Invalido!");
		try {
			String sql = "delete from FEZ where RA = ? and codigoMaterias = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setInt(1,ra);
			BDSQLServer.COMANDO.setInt(2,codMat);
			
			BDSQLServer.COMANDO.executeQuery();
			BDSQLServer.COMANDO.commit();
		}catch(SQLException err) {
			throw new Exception("Erro ao Deletar");
		}
	}
	public static boolean existeMat(int cod) throws Exception{
		boolean resultado = false;
		if(cod < 0)
			throw new Exception("Codigo Invalido!");
		try {
			String sql = "select * from FEZ where codigoMaterias = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setInt(1,cod);
			MeuResultSet resp = (MeuResultSet) BDSQLServer.COMANDO.executeQuery();
			resultado = resp.first();
		}catch(SQLException err) {
			throw new Exception("Erro ao Visualizar!");
		}
		return resultado;
	}
	public static void atualizar(Fez alt) throws Exception
	{
		if(alt == null)
			throw new Exception("Produto a ser Alterado Invalido!");
		if(!existe(alt.getRa()))
			throw new Exception("RA invalido para Atualiza��o!");
		if(!existeMat(alt.getCodigoMateria()))
			throw new Exception("Codigo Invalido!");
		try {
			String sql;
			sql = "UPDATE FEZ SET NOTA = ?, FREQUENCIA = ? WHERE RA = ? and CODIGOMATERIAS = ?";
			BDSQLServer.COMANDO.prepareStatement(sql);
			BDSQLServer.COMANDO.setFloat(1,alt.getNota());
			BDSQLServer.COMANDO.setInt(2,alt.getFrequencia());
			BDSQLServer.COMANDO.setInt(3,alt.getRa());
			BDSQLServer.COMANDO.setInt(4,alt.getCodigoMateria());
			BDSQLServer.COMANDO.executeUpdate();
			BDSQLServer.COMANDO.commit();
		}catch(SQLException erro)
		{
			throw new Exception("Erro ao Atualizar o Dado!");
		}
	}

}
