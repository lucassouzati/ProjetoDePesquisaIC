/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import dominio.Aluno;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class AlunoDAOImp implements AlunoDAO {
    // a conexão com o banco de dados

    private Connection connection;

    public AlunoDAOImp() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Aluno aluno) throws SQLException {
        // prepared statement para inserção
        String sql = "insert into aluno (ID, nome, login, senha) values (?, ?, ?, ?)";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        // seta os valores
        stmt.setInt(1, aluno.getID());
        stmt.setString(2, aluno.getNome());
        stmt.setString(3, aluno.getLogin());
        stmt.setString(4, aluno.getSenha());
        // executa
        stmt.execute();
        stmt.close();
    }

    public void altera(Aluno aluno) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update aluno " +
                "set nome=?, login=?, senha=? where id=?");
        stmt.setString(1, aluno.getNome());
        stmt.setString(2, aluno.getLogin());
        stmt.setString(3, aluno.getSenha());
        stmt.setLong(4, aluno.getID());
        stmt.execute();
        stmt.close();
    }

    public void remove(Aluno aluno) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from aluno where id=?");
        stmt.setLong(1, aluno.getID());
        stmt.execute();
        stmt.close();
    }

    public List<Aluno> getLista() throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from aluno");
        ResultSet rs = stmt.executeQuery();
        List<Aluno> alunos = new ArrayList<Aluno>();
        while (rs.next()) {
            // criando o objeto Contato
            Aluno aluno = new Aluno();
            aluno.setID(rs.getInt("ID"));
            aluno.setNome(rs.getString("nome"));
            aluno.setLogin(rs.getString("login"));
            aluno.setSenha(rs.getString("senha"));
            // adicionando o objeto à lista
            alunos.add(aluno);
        }
        rs.close();
        stmt.close();
        return alunos;
    }

    public List<Aluno> getListaPorDisciplina(int idDisciplina) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT aluno.id, aluno.nome " +
                                                                                      "FROM aluno, alunodisci " +
                                                                                      "WHERE alunodisci.idAluno = aluno.id " +
                                                                                      "AND alunodisci.idDisciplina =" + idDisciplina);
        ResultSet rs = stmt.executeQuery();
        List<Aluno> alunos = new ArrayList<Aluno>();
        while (rs.next()) {
            Aluno aluno = new Aluno();
            aluno.setID(rs.getInt("ID"));
            aluno.setNome(rs.getString("nome"));
            alunos.add(aluno);
        }
        rs.close();
        stmt.close();
        return alunos;
    }


}
