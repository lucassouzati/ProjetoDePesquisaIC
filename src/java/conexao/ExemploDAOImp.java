/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import dominio.Exemplo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class ExemploDAOImp implements ExemploDAO {
    // a conexão com o banco de dados

    private Connection connection;

    public ExemploDAOImp() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Exemplo exemplo) throws SQLException {
        // prepared statement para inserção
        String sql = "insert into exemplo (ID, descricao, IdTermo) values (?, ?, ?)";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        // seta os valores
        stmt.setInt(1, exemplo.getID());
        stmt.setString(2, exemplo.getDescricao());
        stmt.setInt(3, exemplo.getIdTermo());
        // executa
        stmt.execute();
        stmt.close();
    }

    public void altera(Exemplo exemplo) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update exemplo " +
                "set descricao=? where id=?");
        stmt.setString(1, exemplo.getDescricao());
        stmt.setLong(4, exemplo.getID());
        stmt.execute();
        stmt.close();
    }

    public void remove(Exemplo exemplo) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from exemplo where id=?");
        stmt.setLong(1, exemplo.getID());
        stmt.execute();
        stmt.close();
    }

    public void removePorIdTermo(Exemplo exemplo) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from exemplo where idTermo=?");
        stmt.setLong(1, exemplo.getID());
        stmt.execute();
        stmt.close();
    }

    public List<Exemplo> getListaFiltrada(int IdTermo) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from exemplo where IdTermo = " + IdTermo);
        ResultSet rs = stmt.executeQuery();
        List<Exemplo> exemplos = new ArrayList<Exemplo>();
        while (rs.next()) {
            // criando o objeto Contato
            Exemplo exemplo = new Exemplo();
            exemplo.setID(rs.getInt("ID"));
            exemplo.setDescricao(rs.getString("descricao"));
            exemplo.setIdTermo(rs.getInt("IdTermo"));
            // adicionando o objeto à lista
            exemplos.add(exemplo);
        }
        rs.close();
        stmt.close();
        return exemplos;
    }

    public List<Exemplo> getLista() throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from exemplo");
        ResultSet rs = stmt.executeQuery();
        List<Exemplo> exemplos = new ArrayList<Exemplo>();
        while (rs.next()) {
            // criando o objeto Contato
            Exemplo exemplo = new Exemplo();
            exemplo.setID(rs.getInt("ID"));
            exemplo.setDescricao(rs.getString("descricao"));
            exemplo.setIdTermo(rs.getInt("IdTermo"));
            // adicionando o objeto à lista
            exemplos.add(exemplo);
        }
        rs.close();
        stmt.close();
        return exemplos;
    }
}
