/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import dominio.Termo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class TermoDAOImp implements TermoDAO {
    // a conexão com o banco de dados

    private Connection connection;

    public TermoDAOImp() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(Termo Termo) throws SQLException {
        // prepared statement para inserção
        //String sql = "insert into Termo (ID, nome, conceito, exemplo, sinonimo, pergunta, idDisciplina) values (?, ?, ?, ?, ?, ?, ?)";
        String sql = "insert into termo (ID, nome, idDisciplina) values (?, ?, ?)";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        // seta os valores
        stmt.setInt(1, Termo.getID());
        stmt.setObject(2, Termo.getNome());
        //stmt.setObject(3, Termo.getConceito());
        //stmt.setObject(4, Termo.getExemplo());
        //stmt.setObject(5, Termo.getSinonimo());
        //stmt.setObject(6, Termo.getPergunta());
        stmt.setObject(3, Termo.getIdDisciplina());
        // executa
        stmt.execute();
        stmt.close();
    }

    public void altera(Termo Termo) throws SQLException {
        //PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update Termo " +
        //        "set nome=?, conceito=?, exemplo=?, sinonimo=?, pergunta=? where id=?");
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update termo " +
                "set nome=? where id=?");
        stmt.setString(1, Termo.getNome());
        //stmt.setObject(2, Termo.getConceito());
        //stmt.setObject(3, Termo.getExemplo());
        //stmt.setObject(4, Termo.getSinonimo());
        //stmt.setObject(5, Termo.getPergunta());
        //stmt.setLong(6, Termo.getID());
        stmt.setInt(2, Termo.getID());
        stmt.execute();
        stmt.close();
    }

    public void remove(Termo Termo) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from termo where id=?");
        stmt.setLong(1, Termo.getID());
        stmt.execute();
        stmt.close();
    }

    public List<Termo> getListaFiltrada(int idDisciplina) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from termo where idDisciplina = " + idDisciplina);
        ResultSet rs = stmt.executeQuery();
        List<Termo> Termos = new ArrayList<Termo>();
        while (rs.next()) {
            // criando o objeto Contato
            Termo Termo = new Termo();
            Termo.setID(rs.getInt("ID"));
            Termo.setNome(rs.getString("nome"));
            /*Termo.setConceito(rs.getObject("conceito"));
            Termo.setExemplo(rs.getObject("exemplo"));
            Termo.setSinonimo(rs.getObject("sinonimo"));
            Termo.setPergunta(rs.getObject("pergunta"));*/
            // adicionando o objeto à lista
            Termo.setIdDisciplina(rs.getInt("IdDisciplina"));
            Termos.add(Termo);
        }
        rs.close();
        stmt.close();
        return Termos;
    }

    public List<Termo> getLista() throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from termo");
        ResultSet rs = stmt.executeQuery();
        List<Termo> Termos = new ArrayList<Termo>();
        while (rs.next()) {
            // criando o objeto Contato
            Termo Termo = new Termo();
            Termo.setID(rs.getInt("ID"));
            Termo.setNome(rs.getString("nome"));
            /*Termo.setConceito(rs.getObject("conceito"));
            Termo.setExemplo(rs.getObject("exemplo"));
            Termo.setSinonimo(rs.getObject("sinonimo"));
            Termo.setPergunta(rs.getObject("pergunta"));*/
            // adicionando o objeto à lista
            Termo.setIdDisciplina(rs.getInt("IdDisciplina"));
            Termos.add(Termo);
        }
        rs.close();
        stmt.close();
        return Termos;
    }

}
