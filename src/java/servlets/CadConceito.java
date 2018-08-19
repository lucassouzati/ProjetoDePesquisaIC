/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import TextMining.Minerador;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import conexao.ConceitoDAO;
import conexao.ConceitoDAOImp;
import conexao.ConnectionFactory;
import conexao.TermoTempDAO;
import conexao.TermoTempDAOImp;
import dominio.Conceito;
import dominio.TermoTemp;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lefoly
 */
public class CadConceito extends HttpServlet {

    int termoPrincipal, termoRelacionado;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");

        String termos = "";
        String cmd = request.getParameter("cmd");

        String idProfessor = request.getParameter("idProfessor");

        //System.out.println("Recebido no CadConceito: " + idProfessor);

        String idDisciplina = request.getParameter("idDisciplina");
        String nome = request.getParameter("nome");
        String idTermo = request.getParameter("idTermo");
        String descricao = request.getParameter("descricao");

        termoPrincipal = Integer.parseInt(idTermo);

        if (cmd.equals("incluir")) {
            ConceitoDAO dao;
            Conceito conceito = new Conceito();
            try {
                dao = new ConceitoDAOImp();
                List<Conceito> conceitos = dao.getLista();
                Conceito con = new Conceito();
                for (int i=0; i < conceitos.size(); i++) {
                  con = conceitos.get(i);
                }
                //int id = conceitos.size() + 1;
                int id = con.getID() + 1;

                conceito.setID(id);
                conceito.setDescricao(descricao);
                conceito.setIdTermo(Integer.parseInt(idTermo));

                termos = Minerador.FiltrarStopwords(conceito.getDescricao());
                String[] Listatermos = termos.split("\\s");
                for (int i = 0; i <= Listatermos.length-1; i++) {

                    if (pesquisarTermo(Integer.parseInt(idDisciplina), Listatermos[i])) {
                        //Criar Associa��o entre Termos
                        criarAssociacao(termoPrincipal, termoRelacionado);
                    } else {
                        if (! pesquisarTermoTemp(Listatermos[i])) {

                          TermoTempDAO daot;
                          daot = new TermoTempDAOImp();
                          List<TermoTemp> termosT = daot.getLista();
                          int idt = termosT.size() + 1;

                          TermoTemp termotemp = new TermoTemp();
                          termotemp.setID(idt);
                          termotemp.setIdDisciplina(Integer.parseInt(idDisciplina));
                          termotemp.setNome(Listatermos[i]);
                          daot.adiciona(termotemp);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                dao = new ConceitoDAOImp();
                RequestDispatcher rd = null;

                if (cmd.equalsIgnoreCase("incluir")) {
                    dao.adiciona(conceito);
                }

                //System.out.println("Passado no CadConceito: " + idProfessor);

                rd = request.getRequestDispatcher("TermoConceito.jsp?idProfessor=" + idProfessor +
                                                  "&idDisciplina=" + idDisciplina +
                                                  "&idTermo=" + idTermo +
                                                  "&nome=" + nome);
                //"&termos=" + termos);
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        }
    }

    public boolean pesquisarTermo(int Disciplina, String termoP) throws SQLException {
        boolean achou = false;
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = ConnectionFactory.getConnection();
            st = conn.createStatement();
            //Gerar Lista de Termos para a Disciplina, via SQL;
            rs = st.executeQuery("select * from termo where idDisciplina=" + Disciplina);

            rs.last();
            int total = rs.getRow();
            rs.beforeFirst();

            int[] ids = new int[total];
            String[] nomes = new String[total];
            int i = 0;
            while (rs.next()) {
                ids[i] = rs.getInt("id");
                nomes[i] = rs.getString("nome");
                i++;
            } //end while
            //Comparar o termo stemmizado à lista stemmizada;
            for (i = 0; i<=nomes.length-1; i++) {
                String termo1 = Minerador.Radicalizar(termoP);
                String termo2 = Minerador.Radicalizar(nomes[i]);
                if (termo1.equalsIgnoreCase(termo2)) {
                    termoRelacionado = ids[i];
                    achou = true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return achou;
    }

    public void criarAssociacao(int termo1, int termo2) throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = ConnectionFactory.getConnection();
            st = conn.createStatement();
            //Gerar Lista de Termos para a Disciplina, via SQL;

            rs = st.executeQuery("select * from termorelac");

            rs.last();
            
            int id = rs.getRow() + 1;
            int id2 = id + 1;
            
            st.execute("insert into termorelac values (" + id + ", " + termo1 + ", " + termo2 + ")");
            st.execute("insert into termorelac values (" + id2 + ", " + + termo2 + ", " + termo1 + ")");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean pesquisarTermoTemp(String termo) throws SQLException {
        boolean achou = false;
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = ConnectionFactory.getConnection();
            st = conn.createStatement();
            //Gerar Lista de Termos para a Disciplina, via SQL;
            rs = st.executeQuery("select * from termotemp where nome='" + termo + "'");

            rs.beforeFirst();

            while (rs.next()) {
                if (rs.getString("nome").equalsIgnoreCase(termo)) {
                    achou = true;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return achou;
    }

    public String processarConceito(String entrada) throws FileNotFoundException {
        String saida[] = Minerador.FiltrarStopwords(entrada).split(" ");
        String TextoSaida = "";
        for (int i = 0; i < saida.length-1; i++) {
            TextoSaida = TextoSaida + saida[i];
        }
        return TextoSaida;
    }

    // <editor-fold defaultstate="collapsed" desc="Métodos HttpServlet. Clique no sinal de + à esquerda para editar o código.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
