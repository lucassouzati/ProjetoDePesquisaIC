/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import dominio.Sinonimo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class SinonimoDAOImp implements SinonimoDAO {
    // a conexão com o banco de dados

    private Connection connection;

    public SinonimoDAOImp() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Sinonimo sinonimo) throws SQLException {
        // prepared statement para inserção
        String sql = "insert into sinonimo (ID, descricao, IdTermo) values (?, ?, ?)";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        // seta os valores
        stmt.setInt(1, sinonimo.getID());
        stmt.setString(2, sinonimo.getDescricao());
        stmt.setInt(3, sinonimo.getIdTermo());
        // executa
        stmt.execute();
        stmt.close();
    }

    public void altera(Sinonimo sinonimo) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update sinonimo " +
                "set descricao=? where id=?");
        stmt.setString(1, sinonimo.getDescricao());
        stmt.setLong(4, sinonimo.getID());
        stmt.execute();
        stmt.close();
    }

    public void remove(Sinonimo sinonimo) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from sinonimo where id=?");
        stmt.setLong(1, sinonimo.getID());
        stmt.execute();
        stmt.close();
    }

    public void removePorIdTermo(Sinonimo sinonimo) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from sinonimo where idTermo=?");
        stmt.setLong(1, sinonimo.getID());
        stmt.execute();
        stmt.close();
    }

    public List<Sinonimo> getListaFiltrada(int IdTermo) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from sinonimo where IdTermo = " + IdTermo);
        ResultSet rs = stmt.executeQuery();
        List<Sinonimo> sinonimos = new ArrayList<Sinonimo>();
        while (rs.next()) {
            // criando o objeto Contato
            Sinonimo sinonimo = new Sinonimo();
            sinonimo.setID(rs.getInt("ID"));
            sinonimo.setDescricao(rs.getString("descricao"));
            sinonimo.setIdTermo(rs.getInt("IdTermo"));
            // adicionando o objeto à lista
            sinonimos.add(sinonimo);
        }
        rs.close();
        stmt.close();
        return sinonimos;
    }

    public List<Sinonimo> getLista() throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from sinonimo");
        ResultSet rs = stmt.executeQuery();
        List<Sinonimo> sinonimos = new ArrayList<Sinonimo>();
        while (rs.next()) {
            // criando o objeto Contato
            Sinonimo sinonimo = new Sinonimo();
            sinonimo.setID(rs.getInt("ID"));
            sinonimo.setDescricao(rs.getString("descricao"));
            sinonimo.setIdTermo(rs.getInt("IdTermo"));
            // adicionando o objeto à lista
            sinonimos.add(sinonimo);
        }
        rs.close();
        stmt.close();
        return sinonimos;
    }

}
