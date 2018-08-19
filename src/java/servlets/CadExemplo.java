/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import conexao.ExemploDAO;
import conexao.ExemploDAOImp;
import dominio.Exemplo;
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
public class CadExemplo extends HttpServlet {
   
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
        String idProfessor = request.getParameter("idProfessor");
        RequestDispatcher rd = null;

        if (cmd.equals("incluir")) {
            ExemploDAO dao;
            Exemplo exemplo = new Exemplo();
            try {
                dao = new ExemploDAOImp();
                List<Exemplo> exemplos = dao.getLista();
                Exemplo exe = new Exemplo();
                for (int i=0; i < exemplos.size(); i++) {
                  exe = exemplos.get(i);
                }
                //int id = exemplos.size() + 1;
                int id = exe.getID() + 1;

                exemplo.setID(id);
                exemplo.setDescricao(request.getParameter("descricao"));
                exemplo.setIdTermo(Integer.parseInt(idTermo));

                //System.out.println("Exemplo:\n" + exemplo.getDescricao() + "\n");

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                dao = new ExemploDAOImp();

                if (cmd.equalsIgnoreCase("incluir")) {
                    dao.adiciona(exemplo);
                }
                rd = request.getRequestDispatcher("TermoExemplos.jsp?idDisciplina=" + idDisciplina +
                                                                           "&nome=" + nome +
                                                                        "&idTermo=" + idTermo +
                                                                        "&idProfessor=" + idProfessor);

                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        } else if (cmd.equals("excluir")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));  // só é passado na exclusão
                ExemploDAO dao;
                Exemplo exemplo = new Exemplo();
                exemplo.setID(id);
                ExemploDAOImp daoE = new ExemploDAOImp();
                daoE.remove(exemplo);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                rd = request.getRequestDispatcher("TermoExemplos.jsp?idDisciplina=" + idDisciplina +
                                                                           "&nome=" + nome +
                                                                        "&idTermo=" + idTermo +
                                                                        "&idProfessor=" + idProfessor);
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
