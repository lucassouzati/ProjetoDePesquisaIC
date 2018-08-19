/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import conexao.ConceitoDAOImp;
import conexao.ConnectionFactory;
import conexao.ExemploDAOImp;
import conexao.SinonimoDAOImp;
import conexao.TermoDAO;
import conexao.TermoDAOImp;
import dominio.Conceito;
import dominio.Exemplo;
import dominio.Sinonimo;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dominio.Termo;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class CadTermo extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        String cmd = request.getParameter("cmd");
        String nome = request.getParameter("nome");
        int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));
        String idProfessor = request.getParameter("idProfessor");
        int idTermo = 0;

        if (request.getParameter("idTermo") != null) {
            idTermo = Integer.parseInt(request.getParameter("idTermo"));
        }

        if (cmd.equals("incluir")) {
            TermoDAO dao;
            Termo termo = new Termo();
            try {
                dao = new TermoDAOImp();
                List<Termo> termos = dao.getLista();
                Termo ter = new Termo();
                for (int i = 0; i < termos.size(); i++) {
                    ter = termos.get(i);
                }
                int id = ter.getID() + 1;

                termo.setID(id);
                termo.setNome(nome);
                termo.setIdDisciplina(idDisciplina);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                dao = new TermoDAOImp();
                RequestDispatcher rd = null;

                if (cmd.equalsIgnoreCase("incluir")) {
                    dao.adiciona(termo);
                }
                rd = request.getRequestDispatcher("Termos.jsp?idDisciplina=" + idDisciplina);
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        } else if (cmd.equals("excluir")) {
            try {
                apagarTermo(idTermo);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("Termos.jsp?idDisciplina=" + idDisciplina);
                rd.forward(request, response);
            } finally {
                out.close();
            }
        } else if (cmd.equals("editar")){
            try{
                String novonome = request.getParameter("novonome");
                Termo termo = new Termo();
                termo.setID(idTermo);
                termo.setNome(novonome);
                TermoDAOImp termodao = new TermoDAOImp();
                termodao.altera(termo);
                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("TermoConceito.jsp?idDisciplina=" + idDisciplina +
                    "&idTermo=" + idTermo +
                    "&nome=" + nome +
                    "idProfessor=" + idProfessor);
                rd.forward(request, response);
            } finally {
                out.close();
            }
        }
    }

    public static void apagarTermo(int idTermo) throws SQLException {
        Termo termo = new Termo();
        termo.setID(idTermo);
        TermoDAOImp termodao = new TermoDAOImp();
        termodao.remove(termo);

        Conceito conceito = new Conceito();
        conceito.setID(idTermo);
        ConceitoDAOImp conceitodao = new ConceitoDAOImp();
        conceitodao.removePorIdTermo(conceito);

        Exemplo exemplo = new Exemplo();
        exemplo.setID(idTermo);
        ExemploDAOImp exemplodao = new ExemploDAOImp();
        exemplodao.removePorIdTermo(exemplo);

        Sinonimo sinonimo = new Sinonimo();
        sinonimo.setID(idTermo);
        SinonimoDAOImp sinonimodao = new SinonimoDAOImp();
        sinonimodao.removePorIdTermo(sinonimo);

        apagarAssociacao(idTermo);
    }

    public static void apagarAssociacao(int termo) throws SQLException {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = ConnectionFactory.getConnection();
            st = conn.createStatement();
            st.execute("DELETE FROM termorelac " +
                       "WHERE (id1 = " + termo + ") " +
                       "OR (id2 = " + termo + ")");
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
            Logger.getLogger(CadTermo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CadTermo.class.getName()).log(Level.SEVERE, null, ex);
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
