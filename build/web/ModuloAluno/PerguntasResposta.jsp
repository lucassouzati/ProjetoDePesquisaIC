<%-- 
    Document   : PerguntasResposta
    Created on : 30/10/2009, 17:51:09
    Author     : lefoly
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"
        import="java.sql.*, conexao.*, dominio.Pergunta, dominio.Resposta, java.util.List"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Pergunta Respondida</title>
    </head>
    <body>
        <%  int idAluno = Integer.parseInt(request.getParameter("idAluno"));
                    int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));
                    int idPergunta = Integer.parseInt(request.getParameter("idPergunta"));
                    String texto, resp = null;

                    RespostaDAO daoR = new RespostaDAOImp();
                    ResultSet r = daoR.getListaPorAlunoEdisciplinaEpergunta(idDisciplina, idAluno, idPergunta);

                    //System.out.println("respostas.size() = " + r. .size());
                    if (r.next()) {
                        idPergunta = r.getInt("idPergunta");
                        texto = r.getString("pergunta");
                        resp = r.getString("resposta");
        %>
        <table style="text-align: left; width: 100%;" border="0" cellpadding="2" cellspacing="2">
            <tbody>
                <tr>
                    <td align="left"><h2>Pergunta</h2></td>
                    <td align="right"><a href="./Perguntas.jsp?idDisciplina=<%=idDisciplina%>&idAluno=<%=idAluno%>">Voltar</a></td>
                </tr>
            </tbody>
        </table>
        <%=texto%>
        <br>
        <h2>Resposta</h2>
        <%=resp%>
        <br>
        <%                }
                    else
                        System.out.println("sem registros");
        %>
    </body>
</html>

