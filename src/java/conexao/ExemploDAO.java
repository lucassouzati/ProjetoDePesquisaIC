/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import dominio.Exemplo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lefoly
 */
public interface ExemploDAO {

    void adiciona(Exemplo exemplo) throws SQLException;

    void altera(Exemplo exemplo) throws SQLException;

    List<Exemplo> getListaFiltrada(int IdTermo) throws SQLException;

    List<Exemplo> getLista() throws SQLException;

    void remove(Exemplo exemplo) throws SQLException;

}
