/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import dominio.TermoTemp;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lefoly
 */
public interface TermoTempDAO {

    void adiciona(TermoTemp TermoTemp) throws SQLException;

    void altera(TermoTemp TermoTemp) throws SQLException;

    List<TermoTemp> getListaFiltrada(int IdDisciplina) throws SQLException;

    List<TermoTemp> getLista() throws SQLException;

    void remove(TermoTemp TermoTemp) throws SQLException;

    void removePeloNome(TermoTemp TermoTemp) throws SQLException;

}
