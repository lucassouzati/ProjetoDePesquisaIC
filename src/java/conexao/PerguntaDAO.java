/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import dominio.Pergunta;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lefoly
 */
public interface PerguntaDAO {

    void adiciona(Pergunta pergunta) throws SQLException;

    void altera(Pergunta pergunta) throws SQLException;

    List<Pergunta> getListaFiltrada(int IdDisciplina) throws SQLException;

    List<Pergunta> getLista() throws SQLException;

    List<Pergunta> getListaPorId(int id) throws SQLException;

    void remove(Pergunta pergunta) throws SQLException;

}
