/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import dominio.Conceito;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lefoly
 */
public interface ConceitoDAO {

    void adiciona(Conceito conceito) throws SQLException;

    void altera(Conceito conceito) throws SQLException;

    List<Conceito> getListaFiltrada(int IdTermo) throws SQLException;

    List<Conceito> getLista() throws SQLException;

    void remove(Conceito conceito) throws SQLException;

}
