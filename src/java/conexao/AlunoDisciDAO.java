/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import dominio.AlunoDisci;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lefoly
 */
public interface AlunoDisciDAO {

    void adiciona(AlunoDisci alunoDisci) throws SQLException;

    void altera(AlunoDisci alunoDisci) throws SQLException;

    List<AlunoDisci> getLista() throws SQLException;

    void remove(AlunoDisci alunoDisci) throws SQLException;

    void removePorIdAluno(AlunoDisci alunoDisci) throws SQLException;

}