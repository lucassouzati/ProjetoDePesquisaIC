/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import dominio.TermoTemp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class TermoTempDAOImp implements TermoTempDAO {
    // a conexão com o banco de dados

    private Connection connection;

    public TermoTempDAOImp() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(TermoTemp TermoTemp) throws SQLException {
        // prepared statement para inserção
        //String sql = "insert into TermoTemp (ID, nome, conceito, exemplo, sinonimo, pergunta, idDisciplina) values (?, ?, ?, ?, ?, ?, ?)";
        String sql = "insert into termotemp (ID, nome, idDisciplina) values (?, ?, ?)";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        // seta os valores
        stmt.setInt(1, TermoTemp.getID());
        stmt.setObject(2, TermoTemp.getNome());
        //stmt.setObject(3, TermoTemp.getConceito());
        //stmt.setObject(4, TermoTemp.getExemplo());
        //stmt.setObject(5, TermoTemp.getSinonimo());
        //stmt.setObject(6, TermoTemp.getPergunta());
        stmt.setObject(3, TermoTemp.getIdDisciplina());
        // executa
        stmt.execute();
        stmt.close();
    }

    public void altera(TermoTemp TermoTemp) throws SQLException {
        //PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update TermoTemp " +
        //        "set nome=?, conceito=?, exemplo=?, sinonimo=?, pergunta=? where id=?");
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update termotemp " +
                "set nome=? where id=?");
        stmt.setString(1, TermoTemp.getNome());
        //stmt.setObject(2, TermoTemp.getConceito());
        //stmt.setObject(3, TermoTemp.getExemplo());
        //stmt.setObject(4, TermoTemp.getSinonimo());
        //stmt.setObject(5, TermoTemp.getPergunta());
        //stmt.setLong(6, TermoTemp.getID());
        stmt.setInt(2, TermoTemp.getID());
        stmt.execute();
        stmt.close();
    }

    public void remove(TermoTemp TermoTemp) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from termotemp where id=?");
        stmt.setLong(1, TermoTemp.getID());
        stmt.execute();
        stmt.close();
    }

    public void removePeloNome(TermoTemp TermoTemp) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from termotemp where nome=?");
        stmt.setString(1, TermoTemp.getNome());
        stmt.execute();
        stmt.close();
    }

    public List<TermoTemp> getListaFiltrada(int idDisciplina) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from termotemp where idDisciplina = " + idDisciplina);
        ResultSet rs = stmt.executeQuery();
        List<TermoTemp> TermoTemps = new ArrayList<TermoTemp>();
        while (rs.next()) {
            // criando o objeto Contato
            TermoTemp TermoTemp = new TermoTemp();
            TermoTemp.setID(rs.getInt("ID"));
            TermoTemp.setNome(rs.getString("nome"));
            /*TermoTemp.setConceito(rs.getObject("conceito"));
            TermoTemp.setExemplo(rs.getObject("exemplo"));
            TermoTemp.setSinonimo(rs.getObject("sinonimo"));
            TermoTemp.setPergunta(rs.getObject("pergunta"));*/
            // adicionando o objeto à lista
            TermoTemp.setIdDisciplina(rs.getInt("IdDisciplina"));
            TermoTemps.add(TermoTemp);
        }
        rs.close();
        stmt.close();
        return TermoTemps;
    }

    public List<TermoTemp> getLista() throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from termotemp");
        ResultSet rs = stmt.executeQuery();
        List<TermoTemp> TermoTemps = new ArrayList<TermoTemp>();
        while (rs.next()) {
            // criando o objeto Contato
            TermoTemp TermoTemp = new TermoTemp();
            TermoTemp.setID(rs.getInt("ID"));
            TermoTemp.setNome(rs.getString("nome"));
            /*TermoTemp.setConceito(rs.getObject("conceito"));
            TermoTemp.setExemplo(rs.getObject("exemplo"));
            TermoTemp.setSinonimo(rs.getObject("sinonimo"));
            TermoTemp.setPergunta(rs.getObject("pergunta"));*/
            // adicionando o objeto à lista
            TermoTemp.setIdDisciplina(rs.getInt("IdDisciplina"));
            TermoTemps.add(TermoTemp);
        }
        rs.close();
        stmt.close();
        return TermoTemps;
    }

}
