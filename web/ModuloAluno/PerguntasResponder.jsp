<%-- 
    Document   : Perguntas
    Created on : 02/08/2009, 10:31:29
    Author     : lefoly
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"
        import="java.sql.*, conexao.*, dominio.*, java.util.List"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Pergunta a Responder</title>
    </head>
    <body>
        <%  String idAluno = request.getParameter("idAluno");
            String idDisciplina = request.getParameter("idDisciplina");
            int idPergunta = Integer.parseInt(request.getParameter("idPergunta"));
            String texto = null;

            PerguntaDAO daoP = new PerguntaDAOImp();
            List<Pergunta> perguntas = daoP.getLista();
            for (int i = 0; i <= perguntas.size() - 1; i++) {
                if (perguntas.get(i).getID() == idPergunta) {
                    texto = perguntas.get(i).getTexto();
                }
            }
        %>
        <table style="text-align: left; width: 100%;" border="0" cellpadding="2" cellspacing="2">
            <tbody>
                <tr>
                    <td align="left"><h2>Pergunta</h2></td>
                    <td align="right"><a href="./Perguntas.jsp?idDisciplina=<%=idDisciplina%>&idAluno=<%=idAluno%>">Voltar</a></td>
                </tr>
            </tbody>
        </table>

        <form action="CadResposta?cmd=incluir" method="POST">
            <%=texto%>
            <br>
            <h2>Resposta</h2>
            <input type="hidden" name="idDisciplina" value="<%=idDisciplina%>" />
            <input type="hidden" name="idPergunta" value="<%=idPergunta%>" />
            <input type="hidden" name="idAluno" value="<%=idAluno%>" />
            <textarea name="texto" rows="10" cols="100"></textarea>
            <br>
            <input type="submit" value="Enviar" />
        </form>
    </body>
</html>
