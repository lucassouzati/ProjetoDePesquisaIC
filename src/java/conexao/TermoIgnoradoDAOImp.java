/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import dominio.TermoIgnorado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class TermoIgnoradoDAOImp implements TermoIgnoradoDAO {
    // a conexão com o banco de dados

    private Connection connection;

    public TermoIgnoradoDAOImp() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(TermoIgnorado TermoIgnorado) throws SQLException {
        // prepared statement para inserção
        String sql = "insert into termoignorado (ID, nome) values (?, ?)";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        // seta os valores
        stmt.setInt(1, TermoIgnorado.getID());
        stmt.setString(2, TermoIgnorado.getNome());
        // executa
        stmt.execute();
        stmt.close();
    }

    public void altera(TermoIgnorado TermoIgnorado) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update termoignorado " +
                "set nome=? where id=?");
        stmt.setString(1, TermoIgnorado.getNome());
        stmt.setLong(4, TermoIgnorado.getID());
        stmt.execute();
        stmt.close();
    }

    public void remove(TermoIgnorado TermoIgnorado) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from TermoIgnorado where id=?");
        stmt.setLong(1, TermoIgnorado.getID());
        stmt.execute();
        stmt.close();
    }

    public List<TermoIgnorado> getLista() throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from termoignorado");
        ResultSet rs = stmt.executeQuery();
        List<TermoIgnorado> TermoIgnorados = new ArrayList<TermoIgnorado>();
        while (rs.next()) {
            // criando o objeto Contato
            TermoIgnorado TermoIgnorado = new TermoIgnorado();
            TermoIgnorado.setID(rs.getInt("ID"));
            TermoIgnorado.setNome(rs.getString("nome"));
            // adicionando o objeto à lista
            TermoIgnorados.add(TermoIgnorado);
        }
        rs.close();
        stmt.close();
        return TermoIgnorados;
    }
}
