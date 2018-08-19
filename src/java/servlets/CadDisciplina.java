/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import conexao.ConnectionFactory;
import conexao.DisciplinaDAO;
import conexao.DisciplinaDAOImp;
import conexao.PerguntaDAOImp;
import dominio.Disciplina;
import dominio.Pergunta;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lefoly
 */
public class CadDisciplina extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String cmd = request.getParameter("cmd");
        String nome = request.getParameter("nome");
        String idDisciplina = request.getParameter("idDisciplina");
        String idProfessor = request.getParameter("idProfessor");

        if (cmd.equalsIgnoreCase("incluir")) {
            DisciplinaDAO dao;
            Disciplina disciplina = new Disciplina();
            try {
                dao = new DisciplinaDAOImp();
                List<Disciplina> disciplinas = dao.getLista();
                Disciplina dis = new Disciplina();
                for (int i=0; i < disciplinas.size(); i++) {
                  dis = disciplinas.get(i);
                }
                //int id = disciplinas.size() + 1;
                int id = dis.getID() + 1;

                //System.out.println(request.getParameter("idProfessor"));
                disciplina.setID(id);
                disciplina.setNome(nome);
                disciplina.setIdProfessor(Integer.parseInt(idProfessor));

            } catch (Exception ex) {
            }

            try {
                dao = new DisciplinaDAOImp();
                dao.adiciona(disciplina);

                RequestDispatcher rd = request.getRequestDispatcher("DisciplinasProfessor.jsp?idProfessor=" + idProfessor);
                rd.forward(request, response);
            } catch (Exception e) {
                throw new ServletException(e);
            }

        } else if (cmd.equalsIgnoreCase("excluir")) {
            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = ConnectionFactory.getConnection();
                st = conn.createStatement();
                //Gerar Lista de Termos para a Disciplina, via SQL;
                rs = st.executeQuery("select * from termo where idDisciplina=" + idDisciplina);
                rs.last();
                int totalTermos = rs.getRow();

                rs = st.executeQuery("select * from pergunta where idDisciplina=" + idDisciplina);
                rs.last();
                int totalPerguntas = rs.getRow();

                RequestDispatcher rd = request.getRequestDispatcher("DisciplinasExcluir.jsp?totalTermos=" + totalTermos +
                                                                    "&totalPerguntas=" + totalPerguntas +
                                                                    "&idProfessor=" + idProfessor);
                rd.forward(request, response);

            } catch (Exception ex) {
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

        } else if (cmd.equalsIgnoreCase("confirmaExclusao")) {
            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = ConnectionFactory.getConnection();
                st = conn.createStatement();
                
                rs = st.executeQuery("select * from termo where idDisciplina=" + idDisciplina);
                while (rs.next()) {
                  CadTermo.apagarTermo(rs.getInt("id"));
                }
                Disciplina disciplina = new Disciplina();
                disciplina.setID(Integer.parseInt(idDisciplina));
                DisciplinaDAOImp dao = new DisciplinaDAOImp();
                dao.remove(disciplina);

                rs = st.executeQuery("select * from termo where idDisciplina=" + idDisciplina);
                while (rs.next()) {
                  CadTermo.apagarTermo(rs.getInt("id"));
                }
                Pergunta pergunta = new Pergunta();
                pergunta.setidDisciplina(Integer.parseInt(idDisciplina));
                PerguntaDAOImp daoP = new PerguntaDAOImp();
                daoP.remove(pergunta);

                RequestDispatcher rd = request.getRequestDispatcher("DisciplinasProfessor.jsp?idProfessor=" + idProfessor);
                rd.forward(request, response);

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CadDisciplina.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CadDisciplina.class.getName()).log(Level.SEVERE, null, ex);
        }
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
