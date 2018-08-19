<%-- 
    Document   : Perguntas
    Created on : 26/10/2009, 16:23:58
    Author     : lefoly
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"
        import="java.sql.*, conexao.*, dominio.*, java.util.List"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Perguntas</title>
    </head>
    <body>
        <%  int idAluno = Integer.parseInt(request.getParameter("idAluno"));
                    int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));
                    int id;
                    String texto = null;

                    PerguntaDAO daoP = new PerguntaDAOImp();
                    List<Pergunta> perguntas = daoP.getListaFiltrada(idDisciplina);
        %>
        <table style="text-align: left; width: 100%;" border="0" cellpadding="2" cellspacing="2">
            <tbody>
                <tr>
                    <td align="left"><h3>Perguntas</h3></td>
                    <td align="right"><a href="DisciplinasAluno.jsp?idAluno=<%=idAluno%>">Voltar</a></td>
                </tr>
                <tr>
                    <td colspan="2" rowspan="1">
                        <table style="text-align: left; width: 100%;" border="1" cellpadding="2" cellspacing="2">
                            <tbody>
                                <tr>
                                    <td>ID</td>
                                    <td>Pergunta</td>
                                    <td>Status</td>
                                </tr>
                                <tr>
                                    <%
                                                for (int i = 0; i <= perguntas.size() - 1; i++) {
                                                    id = perguntas.get(i).getID();
                                                    texto = perguntas.get(i).getTexto();

                                                    RespostaDAO daoR = new RespostaDAOImp();
                                                    List<Resposta> respostas = daoR.getListaFiltrada(id, idAluno);
                                    %>
                                    <td><%=id%></td>
                                    <td><%=texto%></td>
                                    <td><%  if (respostas.size() > 0) {
                                        %><a href="PerguntasResposta.jsp?idAluno=<%=idAluno%>&idPergunta=<%=id%>&idDisciplina=<%=idDisciplina%>">Resposta</a>
                                        <% } else {
                                        %><a href="PerguntasResponder.jsp?idAluno=<%=idAluno%>&idPergunta=<%=id%>&idDisciplina=<%=idDisciplina%>">Responder</a>
                                        <% }
                                        %></td>
                                </tr>
                                <%  }
                                %>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>
        <a href="../ModuloProfessor/ResultadosDetalhes.jsp?idDisciplina=<%=idDisciplina%>&idAluno=<%=idAluno%>&modulo=Aluno">Resultado</a>
    </body>
</html>

