/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import conexao.ProfessorDAO;
import conexao.ProfessorDAOImp;
import dominio.Professor;
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
public class CadProfessor extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cmd = request.getParameter("cmd");

        if (cmd.equals("incluir")) {
            ProfessorDAO dao;
            Professor professor = new Professor();
            try {
                dao = new ProfessorDAOImp();
                List<Professor> professores = dao.getLista();
                Professor pro = new Professor();
                for (int i=0; i < professores.size(); i++) {
                  pro = professores.get(i);
                }
                //int id = professores.size() + 1;
                int id = pro.getID() + 1;

                professor.setID(id);
                professor.setNome(request.getParameter("nome"));
                professor.setLogin(request.getParameter("login"));
                professor.setSenha(request.getParameter("senha"));

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                dao = new ProfessorDAOImp();
                RequestDispatcher rd = null;

                if (cmd.equalsIgnoreCase("incluir")) {
                    dao.adiciona(professor);
                }
                rd = request.getRequestDispatcher("DisciplinasProfessor.jsp?idProfessor=" + professor.getID());
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        } else if (cmd.equals("validar")) {
            ProfessorDAO dao;
            Professor professor = new Professor();
            try {
                dao = new ProfessorDAOImp();
                List<Professor> professores = dao.getLista();
                boolean achou = false;
                int i = 0;
                String vlogin = request.getParameter("login");
                String vsenha = request.getParameter("senha");
                while ((i <= professores.size() - 1) && (achou == false)) {
                    professor = professores.get(i);
                    //System.out.println(professor.getLogin());
                    if ((professor.getLogin().equals(vlogin)) && (professor.getSenha().equals(vsenha))) {
                        achou = true;
                    } else {
                        i++;
                    }
                }
                //System.out.println(achou);
                RequestDispatcher rd = null;
                if (achou == true) {
                    rd = request.getRequestDispatcher("DisciplinasProfessor.jsp?idProfessor=" + professor.getID());
                } else if (achou == false) {
                    rd = request.getRequestDispatcher("ProfessorInvalido.html");
                }
                rd.forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
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
