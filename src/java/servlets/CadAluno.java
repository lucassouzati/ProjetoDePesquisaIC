/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import conexao.AlunoDAO;
import conexao.AlunoDAOImp;
import conexao.AlunoDisciDAO;
import conexao.AlunoDisciDAOImp;
import conexao.RespostaDAO;
import conexao.RespostaDAOImp;
import dominio.Aluno;
import dominio.AlunoDisci;
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
public class CadAluno extends HttpServlet {

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
            AlunoDAO dao;
            Aluno aluno = new Aluno();
            try {
                dao = new AlunoDAOImp();
                List<Aluno> alunos = dao.getLista();
                Aluno alu = new Aluno();
                for (int i = 0; i < alunos.size(); i++) {
                    alu = alunos.get(i);
                }
                //int id = alunos.size() + 1;
                int id = alu.getID() + 1;

                aluno.setID(id);
                aluno.setNome(request.getParameter("nome"));
                aluno.setLogin(request.getParameter("login"));
                aluno.setSenha(request.getParameter("senha"));

                dao = new AlunoDAOImp();
                dao.adiciona(aluno);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("DisciplinasAluno.jsp?idAluno=" + aluno.getID());
                rd.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        } else if (cmd.equals("validar")) {
            AlunoDAO dao;
            Aluno aluno = new Aluno();
            try {
                dao = new AlunoDAOImp();
                List<Aluno> alunos = dao.getLista();
                boolean achou = false;
                int i = 0;
                String vlogin = request.getParameter("login");
                String vsenha = request.getParameter("senha");
                while ((i <= alunos.size() - 1) && (achou == false)) {
                    aluno = alunos.get(i);
                    //System.out.println(professor.getLogin());
                    if ((aluno.getLogin().equals(vlogin)) && (aluno.getSenha().equals(vsenha))) {
                        achou = true;
                    } else {
                        i++;
                    }
                }
                //System.out.println(achou);
                RequestDispatcher rd = null;
                if (achou == true) {
                    rd = request.getRequestDispatcher("DisciplinasAluno.jsp?idAluno=" + aluno.getID());
                } else if (achou == false) {
                    rd = request.getRequestDispatcher("AlunoInvalido.html");
                }
                rd.forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (cmd.equals("excluir")) {
            String idProfessor = request.getParameter("idProfessor");
            String idDisciplina = request.getParameter("idDisciplina");
            String idAluno = request.getParameter("idAluno");

            AlunoDisciDAO daoD;
            AlunoDisci alunoDisci = new AlunoDisci();

            RespostaDAO daoR;
            Resposta resposta = new Resposta();
            try {
                daoD = new AlunoDisciDAOImp();
                daoR = new RespostaDAOImp();
                alunoDisci.setIdAluno(Integer.parseInt(idAluno));
                resposta.setIdAluno(Integer.parseInt(idAluno));

                List<Resposta> respostas = daoR.getLista();
                for (int i = 0; i <= respostas.size()-1; i++) {
                    resposta = respostas.get(i);
                    if (resposta.getIdAluno() == Integer.parseInt(idAluno)) {
                        daoR.remove(respostas.get(i));
                    }
                }
                daoD.removePorIdAluno(alunoDisci);

                RequestDispatcher rd = request.getRequestDispatcher("Resultados.jsp?idDisciplina=" + idDisciplina + "&idProfessor=" + idProfessor);
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
