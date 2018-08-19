/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import dominio.Professor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lefoly
 */
public interface ProfessorDAO {

    void adiciona(Professor professor) throws SQLException;

    void altera(Professor professor) throws SQLException;

    List<Professor> getLista() throws SQLException;

    void remove(Professor professor) throws SQLException;

}
