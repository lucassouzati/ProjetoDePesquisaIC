/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Leandro
 */

public class ConnectionFactory
{
    private static final String banco = "jdbc:mysql://localhost/ead";
    private static final String usuario = "root";
    private static final String senha = "123";
    private static final String caminho = "";


    public static String getBanco() {
        return banco;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getSenha() {
        return senha;
    }

    public static String getCaminho() {
        return caminho;
    }

  public static Connection getConnection() throws SQLException
  {
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
        //System.out.println("Conectando ao banco");
        return (Connection) DriverManager.getConnection(getBanco(), getUsuario(), getSenha());
    }
    catch (ClassNotFoundException e)
    {
      throw new SQLException(e.getMessage());
    }
  }



}
