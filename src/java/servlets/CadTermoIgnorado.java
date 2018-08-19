/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import conexao.TermoIgnoradoDAO;
import conexao.TermoIgnoradoDAOImp;
import conexao.TermoTempDAO;
import conexao.TermoTempDAOImp;
import dominio.TermoIgnorado;
import dominio.TermoTemp;
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
public class CadTermoIgnorado extends HttpServlet {
   
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

        String idProfessor = request.getParameter("idProfessor");
        String idDisciplina = request.getParameter("idDisciplina");
        String idTermo = request.getParameter("idTermo");  // id do termo da página a ser retornada.
        String nome = request.getParameter("nome");   // nome do termo da página a ser retornada.
        String nomeTemp = request.getParameter("nomeTemp");

        if (cmd.equals("incluir")) {
            TermoIgnoradoDAO dao;
            TermoIgnorado termoIgnorado = new TermoIgnorado();

            TermoTempDAO daot;
            TermoTemp termotemp = new TermoTemp();

            try {
                dao = new TermoIgnoradoDAOImp();
                List<TermoIgnorado> termos = dao.getLista();
                TermoIgnorado ter = new TermoIgnorado();
                for (int i=0; i < termos.size(); i++) {
                  ter = termos.get(i);
                }
                //int id = termos.size() + 1;
                int idt = ter.getID() + 1;

                termoIgnorado.setID(idt);
                termoIgnorado.setNome(request.getParameter("nomeTemp"));
                termoIgnorado.setIdDisciplina(Integer.parseInt(request.getParameter("idDisciplina")));

                daot = new TermoTempDAOImp();
                termotemp.setID(idt);
                termotemp.setNome(request.getParameter("nomeTemp"));
                termotemp.setIdDisciplina(Integer.parseInt(request.getParameter("idDisciplina")));

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                dao = new TermoIgnoradoDAOImp();
                daot = new TermoTempDAOImp();
                RequestDispatcher rd = null;

                if (cmd.equalsIgnoreCase("incluir")) {
                    dao.adiciona(termoIgnorado);
                    daot.removePeloNome(termotemp);
                }
                rd = request.getRequestDispatcher("TermoConceito.jsp?idProfessor=" + idProfessor +
                                                  "&idDisciplina=" + idDisciplina +
                                                  "&idTermo=" + idTermo +
                                                  "&nome=" + nome);
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        }


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
