/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import dominio.Resposta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lefoly
 */
public interface RespostaDAO {

    void adiciona(Resposta resposta) throws SQLException;

    void altera(Resposta resposta) throws SQLException;

    List<Resposta> getListaFiltrada(int idPergunta, int idAluno) throws SQLException;

    List<Resposta> getLista() throws SQLException;

    ResultSet getListaPorAlunoEdisciplina(int idDisciplina, int idAluno) throws SQLException;

    ResultSet getListaPorAlunoEdisciplinaEpergunta(int idDisciplina, int idAluno, int idPergunta) throws SQLException;

    void remove(Resposta resposta) throws SQLException;

}
