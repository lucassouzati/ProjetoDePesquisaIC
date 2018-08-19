/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import dominio.Aluno;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lefoly
 */
public interface AlunoDAO {

    void adiciona(Aluno aluno) throws SQLException;

    void altera(Aluno aluno) throws SQLException;

    List<Aluno> getLista() throws SQLException;

    List<Aluno> getListaPorDisciplina(int idDisciplina) throws SQLException;

    void remove(Aluno aluno) throws SQLException;

}
