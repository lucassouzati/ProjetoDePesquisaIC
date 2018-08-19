/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import dominio.TermoIgnorado;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lefoly
 */
public interface TermoIgnoradoDAO {

    void adiciona(TermoIgnorado TermoIgnorado) throws SQLException;

    void altera(TermoIgnorado TermoIgnorado) throws SQLException;

    List<TermoIgnorado> getLista() throws SQLException;

    void remove(TermoIgnorado TermoIgnorado) throws SQLException;

}
