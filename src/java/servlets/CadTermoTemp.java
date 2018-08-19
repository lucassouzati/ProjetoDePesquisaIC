/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import conexao.TermoDAO;
import conexao.TermoDAOImp;
import conexao.TermoTempDAOImp;
import conexao.ConnectionFactory;
import dominio.Termo;
import dominio.TermoTemp;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Leandro
 */
public class CadTermoTemp extends HttpServlet {

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

        try {
            int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));
            int idTermo = Integer.parseInt(request.getParameter("idTermo"));
            String nome = request.getParameter("nome");
            String idProfessor = request.getParameter("idProfessor");

            TermoDAO dao;
            Termo termo = new Termo();
            dao = new TermoDAOImp();
            List<Termo> termos = dao.getLista();
            Termo ter = new Termo();
            for (int i=0; i < termos.size(); i++) {
              ter = termos.get(i);
            }
            //int id = termos.size() + 1;
            int id = ter.getID() + 1;
            termo.setID(id);
            termo.setNome(pesquisarTermoTemp(idTermo));
            termo.setIdDisciplina(idDisciplina);

            dao = new TermoDAOImp();
            dao.adiciona(termo);

            TermoTemp termotemp = new TermoTemp();
            termotemp.setID(idTermo);
            TermoTempDAOImp daot = new TermoTempDAOImp();
            daot.remove(termotemp);

            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("TermoConceito.jsp?idDisciplina=" + idDisciplina +
                    "&idTermo=" + id +
                    "&nome=" + nome +
                    "idProfessor=" + idProfessor);
            rd.forward(request, response);

        } finally {
            out.close();
        }
    }

    public String pesquisarTermoTemp(int id) throws SQLException {
        String nome = null;

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = ConnectionFactory.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from termotemp where id = " + id);
            while (rs.next()) {
                nome = rs.getString("nome");
            }
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
        return nome;
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
            Logger.getLogger(CadTermoTemp.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CadTermoTemp.class.getName()).log(Level.SEVERE, null, ex);
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
