<%-- 
    Document   : disciplinas
    Created on : 05/07/2009, 10:03:46
    Author     : lefoly
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"
        import="conexao.*, dominio.*, java.util.List" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Disciplinas</title>
        <link rel="stylesheet" type="text/css" href="../geral.css" />
    </head>
    <body>

        <table style="text-align: left; width: 100%; " border="0">
            <tbody>
                <tr>
                    <td align="left"><h3>Disciplinas:</h3></td>
                    <td></td>
                    <td align="right"><a href="../inicio.html">Sair</a></td>
                </tr>
            </tbody>
        </table>

        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                            int idProfessor = Integer.parseInt(request.getParameter("idProfessor"));
                            DisciplinaDAO dao = new DisciplinaDAOImp();
                            List<Disciplina> disciplinas = dao.getListaFiltrada(idProfessor);
                            for (int i = 0; i <= disciplinas.size() - 1; i++) {
                                int id = disciplinas.get(i).getID();
                                String nome = disciplinas.get(i).getNome();
                %>
                <tr>
                    <td><%=id%></td>
                    <td><%=nome%></td>
                    <td><a href="./Termos.jsp?idDisciplina=<%=id%>&idProfessor=<%=idProfessor%>">Acessar</a></td>
                    <td><form action="CadDisciplina?cmd=excluir" method="POST">
                            <input type="hidden" name="idDisciplina" value="<%=id%>">
                            <input type="hidden" name="nome" value="<%=nome%>">
                            <input type="hidden" name="idProfessor" value="<%=idProfessor%>">
                            <input type="submit" value="Excluir" />
                        </form></td>
                </tr>
                <%
                            }
                %>
            </tbody>
        </table>
        <br>
        <br>
        <form action="CadDisciplina?cmd=incluir" method="POST">
            Nova Disciplina<br>
            <input type="hidden" name="idProfessor" value=<%=idProfessor%> />
            Nome: <input type="text" name="nome" value="" /><input type="submit" value="Cadastrar" />
        </form>
    </body>
</html>
