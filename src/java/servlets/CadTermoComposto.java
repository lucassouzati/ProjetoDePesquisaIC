/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import conexao.ConnectionFactory;
import conexao.TermoDAO;
import conexao.TermoDAOImp;
import conexao.TermoTempDAOImp;
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
 * @author Lucas Siqueira
 */
public class CadTermoComposto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        /*out.print("<html><body>");
        out.print("teste");
        out.print("</body></html>");*/
        //try {
            int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));
            //int idTermo = Integer.parseInt(request.getParameter("idTermo"));
            String[] termoscompostos = request.getParameterValues("ckb");
            int[] idTermos= new int[termoscompostos.length];
            String nome="";
            //out.print("<html><body>");
           // out.print("teste");
            for(int j=0; j<termoscompostos.length; j++){
              //  out.println("Tentando imprimir alguma coisa dos termos compostos" + termoscompostos[j]);
                termoscompostos[j] = termoscompostos[j].replace("/", "");
                idTermos[j] = Integer.parseInt(termoscompostos[j]);
            }
            for(int i=0; i<termoscompostos.length; i++){
                nome += pesquisarTermoTemp(idTermos[i]) + " ";
                //out.print("Aqui deveriam estar os termpos compostos" + termoscompostos[i]);
                out.print("E aqui o nome" + nome);
                //termoscompostos[i]= termoscompostos[i].replace("/", "");
                //nome += termoscompostos[i] + " ";
            }
            //out.print("</body></html>");
            //String nome = request.getParameter("nome");
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
            termo.setNome(nome);//(pesquisarTermoTemp(idTermo));
            termo.setIdDisciplina(idDisciplina);

            dao = new TermoDAOImp();
            dao.adiciona(termo);

            TermoTemp termotemp = new TermoTemp();
            TermoTempDAOImp daot = new TermoTempDAOImp();
            for(int j=0; j<idTermos.length; j++){
                termotemp.setID(idTermos[j]);
                daot.remove(termotemp);
            }
            RequestDispatcher rd = null;
            rd = request.getRequestDispatcher("TermoConceito.jsp?idDisciplina=" + idDisciplina +
                    "&idTermo=" + id +
                    "&nome=" + nome +
                    "idProfessor=" + idProfessor);
            rd.forward(request, response);

        //} finally {
            out.close();
        //}
    
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
            Logger.getLogger(CadTermoComposto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
            Logger.getLogger(CadTermoComposto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
