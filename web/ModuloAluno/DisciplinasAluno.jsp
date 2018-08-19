<%-- 
    Document   : disciplinas
    Created on : 05/07/2009, 10:03:46
    Author     : lefoly
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"
        import="java.sql.*, conexao.*, dominio.*, java.util.List" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Disciplinas</title>
    </head>
    <body>
        <p align="right"><a href="../inicio.html">Sair</a></p>
        <h2>Disciplinas</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                            int idAluno = Integer.parseInt(request.getParameter("idAluno"));
                            String disciplinaJaSelecionada = request.getParameter("disciplinaJaSelecionada");

                            DisciplinaDAO daoD = new DisciplinaDAOImp();
                            List<Disciplina> disciplinas1 = daoD.getListaPorAluno(idAluno);

                            for (int i = 0; i < disciplinas1.size(); i++) {
                                int id = disciplinas1.get(i).getID();  //rs.getString("id");
                                String nome = disciplinas1.get(i).getNome(); //rs.getString("nome");
%>
                <tr>
                    <td><%= id%></td>
                    <td><%= nome%></td>
                    <td><a href="./Perguntas.jsp?idDisciplina=<%=id%>&idAluno=<%=idAluno%>">Perguntas</a></td>
                </tr>
                <% } //end for
%>
            </tbody>
        </table>
        <br>
        <br>
        Inscrição em novas Disciplinas<br>
        <br>
        <form action="CadAlunoDisci?cmd=incluir" method="POST">
            <input type="hidden" name="idAluno" value=<%=idAluno%> />
            Selecione uma Disciplina:<br>
            <select name="disciplina">
                <%
                            DisciplinaDAO dao = new DisciplinaDAOImp();
                            List<Disciplina> disciplinas2 = dao.getLista();
                            for (int i = 0; i <= disciplinas2.size() - 1; i++) {
                                String nome = disciplinas2.get(i).getNome();
                %>
                <option><%=nome%></option>
                <%          } //end for
%>
            </select><input type="submit" value="Inscrever-se" />
        </form>
        <%
                    if (disciplinaJaSelecionada != null) {
                        out.println("<h3>Disciplina Já Selecionada.</h3>");
                    }
        %>
    </body>
</html>
