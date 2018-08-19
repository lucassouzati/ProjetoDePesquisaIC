/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import dominio.Resposta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class RespostaDAOImp implements RespostaDAO {
    // a conexão com o banco de dados

    private Connection connection;

    public RespostaDAOImp() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Resposta resposta) throws SQLException {
        // prepared statement para inserção
        String sql = "insert into resposta (ID, idPergunta, idAluno, texto) values (?, ?, ?, ?)";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        // seta os valores
        stmt.setInt(1, resposta.getID());
        stmt.setInt(2, resposta.getIdPergunta());
        stmt.setInt(3, resposta.getIdAluno());
        stmt.setString(4, resposta.getTexto());
        // executa
        stmt.execute();
        stmt.close();
    }

    public void altera(Resposta resposta) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update resposta " +
                "set idPergunta=?, idAluno=?, texto=? where id=?");
        stmt.setInt(1, resposta.getIdPergunta());
        stmt.setInt(2, resposta.getIdAluno());
        stmt.setString(3, resposta.getTexto());
        stmt.setInt(4, resposta.getID());
        stmt.execute();
        stmt.close();
    }

    public void remove(Resposta resposta) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from resposta where id=?");
        stmt.setInt(1, resposta.getID());
        stmt.execute();
        stmt.close();
    }

    public List<Resposta> getListaFiltrada(int idPergunta, int idAluno) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from resposta where idPergunta = " + idPergunta + " and idAluno = " + idAluno);
        System.out.println(stmt.getPreparedSql());
        ResultSet rs = stmt.executeQuery();
        List<Resposta> respostas = new ArrayList<Resposta>();
        while (rs.next()) {
            // criando o objeto Contato
            Resposta resposta = new Resposta();
            resposta.setID(rs.getInt("ID"));
            resposta.setIdAluno(rs.getInt("idAluno"));
            resposta.setIdPergunta(rs.getInt("idPergunta"));
            resposta.setTexto(rs.getString("texto"));
            // adicionando o objeto à lista
            respostas.add(resposta);
        }
        rs.close();
        stmt.close();
        return respostas;
    }

    public ResultSet getListaPorAlunoEdisciplina(int idDisciplina, int idAluno) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT pergunta.texto as pergunta, resposta.texto as resposta " +
                                                                                        "FROM pergunta, resposta " +
                                                                                        "WHERE (resposta.idPergunta = pergunta.id) " +
                                                                                        "AND resposta.idAluno = " + idAluno + " " +
                                                                                        "AND pergunta.idDisciplina = " + idDisciplina);
        System.out.println(stmt.getPreparedSql());
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    public ResultSet getListaPorAlunoEdisciplinaEpergunta(int idDisciplina, int idAluno, int idPergunta) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("SELECT pergunta.id as idPergunta, pergunta.texto as pergunta, resposta.id  as idResposta, resposta.texto as resposta " +
                                                                                        "FROM pergunta, resposta " +
                                                                                        "WHERE (resposta.idPergunta = pergunta.id) " +
                                                                                        "AND resposta.idAluno = " + idAluno + " " +
                                                                                        "AND pergunta.idDisciplina = " + idDisciplina + " " +
                                                                                        "AND idPergunta=" + idPergunta);
        System.out.println(stmt.getPreparedSql());
        ResultSet rs = stmt.executeQuery();
        return rs;
    }


    public List<Resposta> getLista() throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from resposta");
        ResultSet rs = stmt.executeQuery();
        List<Resposta> respostas = new ArrayList<Resposta>();
        while (rs.next()) {
            // criando o objeto Contato
            Resposta resposta = new Resposta();
            resposta.setID(rs.getInt("ID"));
            resposta.setIdPergunta(rs.getInt("idPergunta"));
            resposta.setIdAluno(rs.getInt("idAluno"));
            resposta.setTexto(rs.getString("texto"));
            // adicionando o objeto à lista
            respostas.add(resposta);
        }
        rs.close();
        stmt.close();
        return respostas;
    }
}
