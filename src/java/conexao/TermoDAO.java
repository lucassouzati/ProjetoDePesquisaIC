/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import dominio.Termo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lefoly
 */
public interface TermoDAO {

    void adiciona(Termo Termo) throws SQLException;

    void altera(Termo Termo) throws SQLException;

    List<Termo> getListaFiltrada(int IdDisciplina) throws SQLException;

    List<Termo> getLista() throws SQLException;

    void remove(Termo Termo) throws SQLException;

}
