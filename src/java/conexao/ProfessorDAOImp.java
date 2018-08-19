/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import dominio.Professor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class ProfessorDAOImp implements ProfessorDAO {
    // a conexão com o banco de dados

    private Connection connection;

    public ProfessorDAOImp() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Professor professor) throws SQLException {
        // prepared statement para inserção
        String sql = "insert into professor (ID, nome, login, senha) values (?, ?, ?, ?)";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        // seta os valores
        stmt.setInt(1, professor.getID());
        stmt.setString(2, professor.getNome());
        stmt.setString(3, professor.getLogin());
        stmt.setString(4, professor.getSenha());
        // executa
        stmt.execute();
        stmt.close();
    }

    public void altera(Professor professor) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update professor " +
                "set nome=?, login=?, senha=? where id=?");
        stmt.setString(1, professor.getNome());
        stmt.setString(2, professor.getLogin());
        stmt.setString(3, professor.getSenha());
        stmt.setLong(4, professor.getID());
        stmt.execute();
        stmt.close();
    }

    public void remove(Professor professor) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from professor where id=?");
        stmt.setLong(1, professor.getID());
        stmt.execute();
        stmt.close();
    }

    public List<Professor> getLista() throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from professor");
        ResultSet rs = stmt.executeQuery();
        List<Professor> professores = new ArrayList<Professor>();
        while (rs.next()) {
            // criando o objeto Contato
            Professor professor = new Professor();
            professor.setID(rs.getInt("ID"));
            professor.setNome(rs.getString("nome"));
            professor.setLogin(rs.getString("login"));
            professor.setSenha(rs.getString("senha"));
            // adicionando o objeto à lista
            professores.add(professor);
        }
        rs.close();
        stmt.close();
        return professores;
    }
}
