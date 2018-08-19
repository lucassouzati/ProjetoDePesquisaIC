/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import dominio.Disciplina;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lefoly
 */
public interface DisciplinaDAO {

    void adiciona(Disciplina disciplina) throws SQLException;

    void altera(Disciplina disciplina) throws SQLException;

    List<Disciplina> getListaFiltrada(int IdProfessor) throws SQLException;

    List<Disciplina> getListaPorAluno(int IdAluno) throws SQLException;

    List<Disciplina> getLista() throws SQLException;

    void remove(Disciplina disciplina) throws SQLException;

}
