<%-- 
    Document   : TermoPerguntas
    Created on : 05/07/2009, 18:42:12
    Author     : lefoly
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"
        import="java.sql.*, conexao.*, dominio.*, java.util.List" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%          int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));
            String nome = request.getParameter("nome");
            String idProfessor = request.getParameter("idProfessor");
            int id;
            String texto = "";

            Disciplina disciplina = new Disciplina();
            DisciplinaDAO dao = new DisciplinaDAOImp();
            List<Disciplina> disciplinas = dao.getLista();
            for (int i = 0; i <= disciplinas.size() - 1; i++) {
                if (disciplinas.get(i).getID() == idDisciplina) {
                    nome = disciplinas.get(i).getNome();
                }
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Cadastro de Perguntas</title>
        <link rel="stylesheet" type="text/css" href="../geral.css" />
    </head>
    <body>
        <div class="divCSSTableGenerator">
            <table style="text-align: left; width: 100%; " border="0">
                <tbody>
                    <tr>
                        <td align="left"><h3>Disciplina: <%=nome%></h3></td>
                        <td></td>
                        <td align="right"><a href="DisciplinasProfessor.jsp?idProfessor=<%=idProfessor%>">Voltar para Disciplinas</a></td>
                    </tr>
                </tbody>
            </table>

            <table style="text-align: left; width: 100%; " border="0">
                <tbody>
                    <tr>
                        <td align="left"><a href="./Termos.jsp?idDisciplina=<%=idDisciplina%>&idProfessor=<%=idProfessor%>">LOs</a></td>
                        <td align="left"><h3>Perguntas</h3></td>
                        <td align="left"><a href="./Resultados.jsp?idDisciplina=<%=idDisciplina%>&idProfessor=<%=idProfessor%>">Resultados</a></td>
                    </tr>
                    <tr>
                        <td colspan="3" rowspan="1">
                            <div class="CSSTableGenerator">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td>ID</td>
                                            <td>Texto</td>
                                            <td></td>
                                        </tr>
                                    
                                    
                                        <%
                    Pergunta pergunta = new Pergunta();
                    PerguntaDAO daoP = new PerguntaDAOImp();
                    List<Pergunta> perguntas = daoP.getListaFiltrada(idDisciplina);
                    for (int i = 0; i <= perguntas.size() - 1; i++) {
                            id = perguntas.get(i).getID();
                            texto = perguntas.get(i).getTexto();
                                        %>
                                        <tr>
                                            <td><%=id%></td>
                                            <td><%=texto%></td>
                                            <td><form action="CadPergunta?cmd=excluir" method="POST">
                                                    <input type="hidden" name="idProfessor" value="<%=idProfessor%>">
                                                    <input type="hidden" name="idDisciplina" value="<%=idDisciplina%>">
                                                    <input type="hidden" name="id" value="<%=id%>">
                                                    <input type="submit" value="Excluir" name="Bexcluir" />
                                                </form></td>
                                        </tr>
        <%          } // fim for
        %>
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            <br>
                            <form action="CadPergunta?cmd=incluir" method="POST">
                                Perguntas: <br>
                                <input type="hidden" name="idProfessor" value="<%=idProfessor%>" />
                                <input type="hidden" name="idDisciplina" value="<%=idDisciplina%>" />
                                <input type="hidden" name="nome" value="<%=nome%>" />
                                <input type="text" name="texto" value="" />
                                <input type="submit" value="Cadastrar" />
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
