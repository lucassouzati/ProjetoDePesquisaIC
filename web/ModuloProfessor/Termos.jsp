<%-- 
    Document   : Termos
    Created on : 05/07/2009, 16:34:38
    Author     : lefoly
--%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
        import="conexao.*, conexao.*, dominio.*, java.util.List" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%          int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));
            int idProfessor = Integer.parseInt(request.getParameter("idProfessor"));
            int id;
            String nome = "";

            DisciplinaDAO dao = new DisciplinaDAOImp();
            List<Disciplina> disciplinas = dao.getListaFiltrada(idProfessor);
            for (int i = 0; i <= disciplinas.size() - 1; i++) {
                if (disciplinas.get(i).getID() == idDisciplina) {
                    nome = disciplinas.get(i).getNome();
                }
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Cadastro de LOs</title>
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
                        <td align="left"><h3>LOs</h3></td>
                        <td align="left"><a href="./TermoPerguntas.jsp?idDisciplina=<%=idDisciplina%>&nome=<%=nome%>&idProfessor=<%=idProfessor%>">Perguntas</a></td>
                        <td align="left"><a href="./Resultados.jsp?idDisciplina=<%=idDisciplina%>&idProfessor=<%=idProfessor%>">Resultados</a></td>
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
                                    
                                    
        <%
                    TermoDAO daoT = new TermoDAOImp();
                    List<Termo> termos = daoT.getListaFiltrada(idDisciplina);
                    for (int i = 0; i <= termos.size() - 1; i++) {
                        id = termos.get(i).getID();
                        nome = termos.get(i).getNome();
        %>
                                        <tr>
                                            <td><%=id%></td>
                                            <td><%=nome%></td>
                                            <td><a href="./TermoConceito.jsp?idDisciplina=<%=idDisciplina%>&idTermo=<%=id%>&nome=<%=nome%>&idProfessor=<%=idProfessor%>">Detalhes</a></td>
                                            <td><form action="CadTermo?cmd=excluir" method="POST">
                                                    <input type="hidden" name="idDisciplina" value="<%=idDisciplina%>" />
                                                    <input type="hidden" name="idTermo" value="<%=id%>" />
                                                    <input type="hidden" name="idProfessor" value="<%=idProfessor%>" />
                                                    <input type="submit" value="Excluir" />
                                                </form>
                                            </td>
                                        </tr>
        <%          }  // fim for
        %>
                                    </tbody>
                                </table>
                            </div>
                            <br>
                            <br>
                            <br>
                            <form action="CadTermo?cmd=incluir" method="POST">
                                Novo LO:<br>
                                <input type="hidden" name="idProfessor" value=<%=idProfessor%> />
                                <input type="hidden" name="idDisciplina" value=<%=idDisciplina%> />
                                Nome: <input type="text" name="nome" value="" /><input type="submit" value="Cadastrar" />
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
     </div>
    </body>
</html>