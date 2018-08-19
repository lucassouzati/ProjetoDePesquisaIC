/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import dominio.Pergunta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class PerguntaDAOImp implements PerguntaDAO {
    // a conexão com o banco de dados

    private Connection connection;

    public PerguntaDAOImp() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Pergunta pergunta) throws SQLException {
        // prepared statement para inserção
        String sql = "insert into pergunta (ID, texto, idDisciplina) values (?, ?, ?)";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        // seta os valores
        stmt.setInt(1, pergunta.getID());
        stmt.setString(2, pergunta.getTexto());
        stmt.setInt(3, pergunta.getidDisciplina());
        // executa
        stmt.execute();
        stmt.close();
    }

    public void altera(Pergunta pergunta) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update pergunta " +
                "set texto=? where id=?");
        stmt.setString(1, pergunta.getTexto());
        stmt.setLong(4, pergunta.getID());
        stmt.execute();
        stmt.close();
    }

    public void remove(Pergunta pergunta) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from pergunta where id=?");
        stmt.setLong(1, pergunta.getID());
        stmt.execute();
        stmt.close();
    }

    public void removePoridDisciplina(Pergunta pergunta) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from pergunta where idDisciplina=?");
        stmt.setLong(1, pergunta.getID());
        stmt.execute();
        stmt.close();
    }

    public List<Pergunta> getListaFiltrada(int idDisciplina) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from pergunta where idDisciplina = " + idDisciplina);
        ResultSet rs = stmt.executeQuery();
        List<Pergunta> perguntas = new ArrayList<Pergunta>();
        while (rs.next()) {
            // criando o objeto Contato
            Pergunta pergunta = new Pergunta();
            pergunta.setID(rs.getInt("ID"));
            pergunta.setTexto(rs.getString("texto"));
            pergunta.setidDisciplina(rs.getInt("idDisciplina"));
            // adicionando o objeto à lista
            perguntas.add(pergunta);
        }
        rs.close();
        stmt.close();
        return perguntas;
    }

    public List<Pergunta> getLista() throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from pergunta");
        ResultSet rs = stmt.executeQuery();
        List<Pergunta> perguntas = new ArrayList<Pergunta>();
        while (rs.next()) {
            // criando o objeto Contato
            Pergunta pergunta = new Pergunta();
            pergunta.setID(rs.getInt("ID"));
            pergunta.setTexto(rs.getString("texto"));
            pergunta.setidDisciplina(rs.getInt("idDisciplina"));
            // adicionando o objeto à lista
            perguntas.add(pergunta);
        }
        rs.close();
        stmt.close();
        return perguntas;
    }

        public List<Pergunta> getListaPorId(int id) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from pergunta");
        ResultSet rs = stmt.executeQuery();
        List<Pergunta> perguntas = new ArrayList<Pergunta>();
        while (rs.next()) {
            // criando o objeto Contato
            Pergunta pergunta = new Pergunta();
            pergunta.setID(rs.getInt("ID"));
            pergunta.setTexto(rs.getString("texto"));
            pergunta.setidDisciplina(rs.getInt("idDisciplina"));
            // adicionando o objeto à lista
            perguntas.add(pergunta);
        }
        rs.close();
        stmt.close();
        return perguntas;
    }


}
