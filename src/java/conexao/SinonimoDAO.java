/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import dominio.Sinonimo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lefoly
 */
public interface SinonimoDAO {

    void adiciona(Sinonimo sinonimo) throws SQLException;

    void altera(Sinonimo sinonimo) throws SQLException;

    List<Sinonimo> getListaFiltrada(int IdTermo) throws SQLException;

    List<Sinonimo> getLista() throws SQLException;

    void remove(Sinonimo sinonimo) throws SQLException;

}
