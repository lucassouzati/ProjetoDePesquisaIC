/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import dominio.Conceito;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class ConceitoDAOImp implements ConceitoDAO {
    // a conexão com o banco de dados

    private Connection connection;

    public ConceitoDAOImp() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Conceito conceito) throws SQLException {
        // prepared statement para inserção
        String sql = "insert into conceito (ID, descricao, IdTermo) values (?, ?, ?)";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        // seta os valores
        stmt.setInt(1, conceito.getID());
        stmt.setString(2, conceito.getDescricao());
        stmt.setInt(3, conceito.getIdTermo());
        // executa
        stmt.execute();
        stmt.close();
    }

    public void altera(Conceito conceito) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update conceito " +
                "set descricao=? where id=?");
        stmt.setString(1, conceito.getDescricao());
        stmt.setLong(4, conceito.getID());
        stmt.execute();
        stmt.close();
    }

    public void remove(Conceito conceito) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from conceito where id=?");
        stmt.setLong(1, conceito.getID());
        stmt.execute();
        stmt.close();
    }

    public void removePorIdTermo(Conceito conceito) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from conceito where idTermo=?");
        stmt.setLong(1, conceito.getID());
        stmt.execute();
        stmt.close();
    }


    public List<Conceito> getListaFiltrada(int IdTermo) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from conceito where IdTermo = " + IdTermo);
        ResultSet rs = stmt.executeQuery();
        List<Conceito> conceitos = new ArrayList<Conceito>();
        while (rs.next()) {
            // criando o objeto Contato
            Conceito conceito = new Conceito();
            conceito.setID(rs.getInt("ID"));
            conceito.setDescricao(rs.getString("descricao"));
            conceito.setIdTermo(rs.getInt("IdTermo"));
            // adicionando o objeto à lista
            conceitos.add(conceito);
        }
        rs.close();
        stmt.close();
        return conceitos;
    }

    public List<Conceito> getLista() throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from conceito");
        ResultSet rs = stmt.executeQuery();
        List<Conceito> conceitos = new ArrayList<Conceito>();
        while (rs.next()) {
            // criando o objeto Contato
            Conceito conceito = new Conceito();
            conceito.setID(rs.getInt("ID"));
            conceito.setDescricao(rs.getString("descricao"));
            conceito.setIdTermo(rs.getInt("IdTermo"));
            // adicionando o objeto à lista
            conceitos.add(conceito);
        }
        rs.close();
        stmt.close();
        return conceitos;
    }
}
