/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import dominio.Disciplina;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class DisciplinaDAOImp implements DisciplinaDAO {
    // a conexão com o banco de dados

    private Connection connection;

    public DisciplinaDAOImp() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Disciplina disciplina) throws SQLException {
        // prepared statement para inserção
        String sql = "insert into disciplina (ID, nome, idProfessor) values (?, ?, ?)";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        // seta os valores
        stmt.setInt(1, disciplina.getID());
        stmt.setString(2, disciplina.getNome());
        stmt.setInt(3, disciplina.getIdProfessor());
        // executa
        stmt.execute();
        stmt.close();
    }

    public void altera(Disciplina disciplina) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update disciplina " +
                "set nome=? where id=?");
        stmt.setString(1, disciplina.getNome());
        stmt.setLong(4, disciplina.getID());
        stmt.execute();
        stmt.close();
    }

    public void remove(Disciplina disciplina) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from disciplina where id=?");
        stmt.setLong(1, disciplina.getID());
        stmt.execute();
        stmt.close();
    }

    public List<Disciplina> getListaFiltrada(int IdProfessor) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from disciplina where IdProfessor =" + IdProfessor);
        ResultSet rs = stmt.executeQuery();
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        while (rs.next()) {
            // criando o objeto Contato
            Disciplina disciplina = new Disciplina();
            disciplina.setID(rs.getInt("ID"));
            disciplina.setNome(rs.getString("nome"));
            disciplina.setIdProfessor(rs.getInt("idProfessor"));
            // adicionando o objeto à lista
            disciplinas.add(disciplina);
        }
        rs.close();
        stmt.close();
        return disciplinas;
    }

    public List<Disciplina> getListaPorAluno(int IdAluno) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT disciplina.id, disciplina.nome " +
                                                                                      "FROM disciplina, alunodisci " +
                                                                                      "WHERE alunodisci.idDisciplina = disciplina.id " +
                                                                                      "AND alunodisci.idAluno = " + IdAluno);
        ResultSet rs = stmt.executeQuery();
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        while (rs.next()) {
            // criando o objeto Contato
            Disciplina disciplina = new Disciplina();
            disciplina.setID(rs.getInt("ID"));
            disciplina.setNome(rs.getString("nome"));
            // adicionando o objeto à lista
            disciplinas.add(disciplina);
        }
        rs.close();
        stmt.close();
        return disciplinas;
    }

    public List<Disciplina> getLista() throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from disciplina");
        ResultSet rs = stmt.executeQuery();
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();
        while (rs.next()) {
            // criando o objeto Contato
            Disciplina disciplina = new Disciplina();
            disciplina.setID(rs.getInt("ID"));
            disciplina.setNome(rs.getString("nome"));
            disciplina.setIdProfessor(rs.getInt("idProfessor"));
            // adicionando o objeto à lista
            disciplinas.add(disciplina);
        }
        rs.close();
        stmt.close();
        return disciplinas;
    }
}
