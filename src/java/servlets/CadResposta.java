/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import conexao.RespostaDAO;
import conexao.RespostaDAOImp;
import dominio.Resposta;
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
public class CadResposta extends HttpServlet {
   
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

        if (cmd.equals("incluir")) {
            RespostaDAO dao;
            Resposta resposta = new Resposta();
            try {
                dao = new RespostaDAOImp();
                List<Resposta> respostas = dao.getLista();
                Resposta res = new Resposta();
                for (int i=0; i < respostas.size(); i++) {
                  res = respostas.get(i);
                }
                //int id = respostas.size() + 1;
                int id = res.getID() + 1;

                resposta.setID(id);
                resposta.setIdPergunta(Integer.parseInt(request.getParameter("idPergunta")));
                resposta.setIdAluno(Integer.parseInt(request.getParameter("idAluno")));
                resposta.setTexto(request.getParameter("texto"));

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                dao = new RespostaDAOImp();
                RequestDispatcher rd = null;

                if (cmd.equalsIgnoreCase("incluir")) {
                    dao.adiciona(resposta);
                }
                rd = request.getRequestDispatcher("Perguntas.jsp?idDisciplina=" + request.getParameter("idDisciplina") + 
                                                  "&idAluno=" + request.getParameter("idAluno"));
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
