<%-- 
    Document   : resultados
    Created on : 12/10/2009, 17:57:34
    Author     : lefoly
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"
        import="java.sql.*, conexao.*, dominio.*, java.util.List" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%          int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));
            String idProfessor = request.getParameter("idProfessor");
            String nome = null;

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
        <title>Cadastro de Termos</title>
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
                        <td align="left"><a href="./TermoPerguntas.jsp?idDisciplina=<%=idDisciplina%>&nome=<%=nome%>&idProfessor=<%=idProfessor%>">Perguntas</a></td>
                        <td align="left"><h3>Resultados</h3></td>
                    </tr>
                    <tr>
                        <td colspan="3" rowspan="1">
                            
                            <div class="CSSTableGenerator">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td>ID</td>
                                            <td>Nome</td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                    
                                        <%              AlunoDAO daoA = new AlunoDAOImp();
                                                    List<Aluno> alunos = daoA.getListaPorDisciplina(idDisciplina);
                                                    for (int i = 0; i < alunos.size(); i++) {
                                                        int id = alunos.get(i).getID();
                                                        nome = alunos.get(i).getNome();
                                        %>
                                        <tr>
                                            <td><%=id%></td>
                                            <td><%=nome%></td>
                                            <td><a href="./ResultadosDetalhes.jsp?idDisciplina=<%=idDisciplina%>&idAluno=<%=id%>&idProfessor=<%=idProfessor%>&modulo=Professor">Detalhes</a></td>
                                            <td><form action="CadAluno?cmd=excluir" method="POST">
                                                    <input type = "hidden" name="idProfessor" value="<%=idProfessor%>">
                                                    <input type = "hidden" name="idDisciplina" value="<%=idDisciplina%>">
                                                    <input type = "hidden" name="idAluno" value="<%=id%>">
                                                    <input type="submit" value="Excluir" />
                                                </form>

                                        </tr>
                                        <% } //end for
        %>
                                </table>
                            </div>
                </tbody>
            </table>
        </div>
    </body>
</html>
