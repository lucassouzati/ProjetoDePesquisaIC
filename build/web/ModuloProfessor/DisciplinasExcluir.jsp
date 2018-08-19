<%-- 
    Document   : DisciplinasExcluir
    Created on : 23/10/2009, 15:50:39
    Author     : Leandro
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
            String totalTermos = request.getParameter("totalTermos");
            String totalPerguntas = request.getParameter("totalPerguntas");
            String idProfessor = request.getParameter("idProfessor");
            String idDisciplina = request.getParameter("idDisciplina");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Exclusão de Disciplina</title>
        <link rel="stylesheet" type="text/css" href="../geral.css" />
    </head>
    <body>
        <div class="divCSSTableGenerator">
            <h2>Atenção!</h2>
            <h3>Há <%=totalTermos%> Termos e <%=totalPerguntas%> perguntas cadastradas para essa disciplina.</h3>
            <h3>Confirma a Exclusão?</h3>
            <table border="0">
                <tbody>
                    <tr>
                        <td><form action="CadDisciplina?cmd=confirmaExclusao" method="POST">
                                <input type="hidden" name="idDisciplina" value="<%=idDisciplina%>">
                                <input type="hidden" name="idProfessor" value="<%=idProfessor%>">
                                <input type="submit" value="Sim" />
                            </form></td>
                        <td><form action="DisciplinasProfessor.jsp?idProfessor=<%=idProfessor%>" method="POST">
                                <input type="submit" value="Não" />
                            </form></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
