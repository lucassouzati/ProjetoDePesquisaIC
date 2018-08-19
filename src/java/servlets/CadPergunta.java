/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import conexao.PerguntaDAO;
import conexao.PerguntaDAOImp;
import dominio.Pergunta;
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
public class CadPergunta extends HttpServlet {

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
        String nome = request.getParameter("nome");
        String texto = request.getParameter("texto");
        RequestDispatcher rd = null;

        if (cmd.equals("incluir")) {
            PerguntaDAO dao;
            Pergunta pergunta = new Pergunta();
            try {
                dao = new PerguntaDAOImp();
                List<Pergunta> perguntas = dao.getLista();
                Pergunta per = new Pergunta();
                for (int i=0; i < perguntas.size(); i++) {
                  per = perguntas.get(i);
                }
                //int id = perguntas.size() + 1;
                int id = per.getID() + 1;

                pergunta.setID(id);
                pergunta.setTexto(texto);
                pergunta.setidDisciplina(Integer.parseInt(idDisciplina));

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                dao = new PerguntaDAOImp();

                if (cmd.equalsIgnoreCase("incluir")) {
                    dao.adiciona(pergunta);
                }
                rd = request.getRequestDispatcher("TermoPerguntas.jsp?idDisciplina=" + idDisciplina +
                        "&nome=" + nome);
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        } else if (cmd.equals("excluir")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));  // só é passado na exclusão
                PerguntaDAO dao;
                Pergunta pergunta = new Pergunta();
                pergunta.setID(id);
                PerguntaDAOImp daop = new PerguntaDAOImp();
                daop.remove(pergunta);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                rd = request.getRequestDispatcher("TermoPerguntas.jsp?idDisciplina=" + idDisciplina +
                                                  "&nome=" + nome);
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
