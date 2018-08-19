/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import conexao.SinonimoDAO;
import conexao.SinonimoDAOImp;
import dominio.Sinonimo;
import java.io.IOException;
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
public class CadSinonimo extends HttpServlet {
   
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

        String cmd = request.getParameter("cmd");
        String idDisciplina = request.getParameter("idDisciplina");
        String nome = request.getParameter("nome");
        String idTermo = request.getParameter("idTermo");
        RequestDispatcher rd = null;

        if (cmd.equals("incluir")) {
            SinonimoDAO dao;
            Sinonimo sinonimo = new Sinonimo();
            try {
                dao = new SinonimoDAOImp();
                List<Sinonimo> sinonimos = dao.getLista();
                Sinonimo sin = new Sinonimo();
                for (int i=0; i < sinonimos.size(); i++) {
                  sin = sinonimos.get(i);
                }
                //int id = sinonimos.size() + 1;
                int id = sin.getID() + 1;

                sinonimo.setID(id);
                sinonimo.setDescricao(request.getParameter("descricao"));
                sinonimo.setIdTermo(Integer.parseInt(request.getParameter("idTermo")));

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                dao = new SinonimoDAOImp();

                if (cmd.equalsIgnoreCase("incluir")) {
                    dao.adiciona(sinonimo);
                }
                rd = request.getRequestDispatcher("TermoSinonimos.jsp?idDisciplina=" + idDisciplina +
                                                                            "&nome=" + nome +
                                                                         "&idTermo=" + idTermo);
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        } else if (cmd.equals("excluir")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));  // só é passado na exclusão
                SinonimoDAO dao;
                Sinonimo sinonimo = new Sinonimo();
                sinonimo.setID(id);
                SinonimoDAOImp daoS = new SinonimoDAOImp();
                daoS.remove(sinonimo);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                rd = request.getRequestDispatcher("TermoSinonimos.jsp?idDisciplina=" + idDisciplina +
                                                                            "&nome=" + nome +
                                                                         "&idTermo=" + idTermo);
                rd.forward(request, response);
            }
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="MÃ©todos HttpServlet. Clique no sinal de + Ã  esquerda para editar o cÃ³digo.">
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
