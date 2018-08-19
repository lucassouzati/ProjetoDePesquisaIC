<%-- 
    Document   : ResultadosDetalhes
    Created on : 13/10/2009, 08:54:49
    Author     : lefoly
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"
        import="java.sql.*, conexao.*, TextMining.*, java.text.DecimalFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%          int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));
            int idAluno = Integer.parseInt(request.getParameter("idAluno"));
            String idProfessor = request.getParameter("idProfessor");
            String modulo = request.getParameter("modulo");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Resultados (Detalhes)</title>
        <link rel="stylesheet" type="text/css" href="../geral.css" />
    </head>
    <body>
        <div class="divCSSTableGenerator">
            <table style="text-align: left; width: 100%; " border="0">
                <tbody>
                    <tr>
                        <td align="left"><h3>Resultados (Detalhes)</h3></td>
                        <td></td>
                        <% if (modulo.equals("Professor")) { %>
                          <td align="right"><a href="./Resultados.jsp?idDisciplina=<%=idDisciplina%>&idProfessor=<%=idProfessor%>">Voltar</a></td>
                        <% } else if (modulo.equals("Aluno")) { %>
                          <td align="right"><a href="../ModuloAluno/Perguntas.jsp?idDisciplina=<%=idDisciplina%>&idAluno=<%=idAluno%>">Voltar</a></td>
                        <% } %>
                    </tr>
                </tbody>
            </table>
            
            <%
                ResultSet rs = null;
                DecimalFormat df  = new DecimalFormat("#0.0");

                    RespostaDAO daoR = new RespostaDAOImp();
                    rs =  daoR.getListaPorAlunoEdisciplina(idDisciplina, idAluno);
                    while (rs.next()) {
                        String pergunta = rs.getString("pergunta");
                        String resposta = rs.getString("resposta");
                        Comparador.Processar(pergunta, resposta, idDisciplina);
                        //Comparador.processarPorEtiquetador(pergunta, resposta, idDisciplina);
                        String resultado = df.format((Comparador.resultado)/10);
            %>
            <div class="CSSTableGenerator">
                <table>
                    
                        <tr>
                            <td>Pergunta:<br><%=pergunta%></td>
                        </tr>
                        <tr>
                            <td>Resposta:<br><%=resposta%></td>
                        </tr>
                    
                </table>
                <p align="right">Nota sugerida pelo Sistema: <%=resultado%></p>
                <br>
            </div>
                <% } //end while
                %>
            
        </div>
    </body>
</html>

