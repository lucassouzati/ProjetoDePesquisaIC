/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import conexao.AlunoDisciDAO;
import conexao.AlunoDisciDAOImp;
import conexao.DisciplinaDAO;
import conexao.DisciplinaDAOImp;
import dominio.AlunoDisci;
import dominio.Disciplina;
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
public class CadAlunoDisci extends HttpServlet {

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
        String nomeDisci = request.getParameter("disciplina");
        int idAluno = Integer.parseInt(request.getParameter("idAluno"));

        if (cmd.equals("incluir")) {
            int idDisciplina = 0;

            try {
                //Busca o ID da disciplina baseando-se no nome
                DisciplinaDAO discidao = new DisciplinaDAOImp();
                List<Disciplina> disciplinas = discidao.getLista();
                int i = 0;
                while ((!disciplinas.get(i).getNome().equals(nomeDisci)) &&
                        (i < disciplinas.size())) {
                    i++;
                }
                idDisciplina = disciplinas.get(i).getID();
            } catch (Exception f) {
                f.printStackTrace();
            }

            AlunoDisciDAO dao;
            AlunoDisci alunoDisci = new AlunoDisci();
            boolean achou = false;
            try {
                dao = new AlunoDisciDAOImp();
                List<AlunoDisci> alunos = dao.getLista();
                AlunoDisci alu = new AlunoDisci();
                for (int i = 0; i < alunos.size(); i++) {
                    alu = alunos.get(i);
                    if ((alu.getIdAluno() == idAluno) &&
                            (alu.getIdDisciplina() == idDisciplina)) {
                        achou = true;
                    }
                }

                RequestDispatcher rd = null;

                if (achou == false) {
                    int id = alu.getID() + 1;

                    alunoDisci.setID(id);
                    alunoDisci.setIdAluno(idAluno);
                    alunoDisci.setIdDisciplina(idDisciplina);

                    dao = new AlunoDisciDAOImp();
                    dao.adiciona(alunoDisci);

                    rd = request.getRequestDispatcher("DisciplinasAluno.jsp?idAluno=" + alunoDisci.getIdAluno());

                } else {
                    rd = request.getRequestDispatcher("DisciplinasAluno.jsp?idAluno=" + idAluno + "&disciplinaJaSelecionada=sim");
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
            throws ServletException,
            IOException {
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
            throws ServletException,
            IOException {
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
