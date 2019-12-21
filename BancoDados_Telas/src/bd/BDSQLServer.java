package bd;

import bd.core.*;
//import daos.*;

public class BDSQLServer
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
    	MeuPreparedStatement comando = null;

    	try
        {
            comando =
            new MeuPreparedStatement (
            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            "jdbc:sqlserver://regulus.cotuca.unicamp.br:1433;databasename=BD19372",
            "BD19372", "*****");
        }
        catch (Exception erro)
        {
            System.err.println (erro.getMessage());
            System.exit(0); // aborta o programa
        }
        
        COMANDO = comando;
    }
}