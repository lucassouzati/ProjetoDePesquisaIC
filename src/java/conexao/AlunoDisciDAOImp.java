    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import dominio.AlunoDisci;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leandro
 */
public class AlunoDisciDAOImp implements AlunoDisciDAO {
    // a conexão com o banco de dados

    private Connection connection;

    public AlunoDisciDAOImp() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adiciona(AlunoDisci alunoDisci) throws SQLException {
        // prepared statement para inserção
        String sql = "insert into alunodisci (ID, idAluno, idDisciplina) values (?, ?, ?)";
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
        // seta os valores
        stmt.setInt(1, alunoDisci.getID());
        stmt.setInt(2, alunoDisci.getIdAluno());
        stmt.setInt(3, alunoDisci.getIdDisciplina());
        // executa
        stmt.execute();
        stmt.close();
    }

    public void altera(AlunoDisci alunoDisci) throws SQLException {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("update alunodisci " +
                "set idAluno=?, idDisicplina=? where id=?");
        stmt.setInt(1, alunoDisci.getIdAluno());
        stmt.setInt(2, alunoDisci.getIdDisciplina());
        stmt.setInt(3, alunoDisci.getID());
        stmt.execute();
        stmt.close();
    }

    public void remove(AlunoDisci alunoDisci) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from alunodisci where id=?");
        stmt.setInt(1, alunoDisci.getID());
        stmt.execute();
        stmt.close();
    }

    public void removePorIdAluno(AlunoDisci alunoDisci) throws SQLException
    {
        PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("delete from alunodisci where idAluno=?");
        stmt.setInt(1, alunoDisci.getIdAluno());
        System.out.println(stmt.toString());
        stmt.execute();
        stmt.close();
    }

    public List<AlunoDisci> getLista() throws SQLException {
        PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from alunodisci");
        ResultSet rs = stmt.executeQuery();
        List<AlunoDisci> AlunoDiscis = new ArrayList<AlunoDisci>();
        while (rs.next()) {
            // criando o objeto Contato
            AlunoDisci alunoDisci = new AlunoDisci();
            alunoDisci.setID(rs.getInt("ID"));
            alunoDisci.setIdAluno(rs.getInt("idAluno"));
            alunoDisci.setIdDisciplina(rs.getInt("idDisciplina"));
            // adicionando o objeto à lista
            AlunoDiscis.add(alunoDisci);
        }
        rs.close();
        stmt.close();
        return AlunoDiscis;
    }
}
