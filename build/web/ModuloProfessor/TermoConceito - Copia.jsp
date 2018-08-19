<%-- 
    Document   : TermoDetalhes
    Created on : 05/07/2009, 18:28:37
    Author     : lefoly
--%>

<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
        import="java.sql.*, conexao.*, dominio.*, java.util.List" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<% int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));
            String nome = null;
            int idTermo = Integer.parseInt(request.getParameter("idTermo"));
            String idProfessor = request.getParameter("idProfessor");
            String descricao = "";

            ConceitoDAO dao = new ConceitoDAOImp();
            List<Conceito> conceitos = dao.getListaFiltrada(idTermo);
            if (!conceitos.isEmpty()) {
                descricao = conceitos.get(0).getDescricao();
            }
            TermoDAO daoT = new TermoDAOImp();
            List<Termo> termos = daoT.getLista();
            for (int i = 0; i <= termos.size() - 1; i++) {
                if (termos.get(i).getID() == idTermo) {
                    nome = termos.get(i).getNome();
                }
            }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Cadastro de Definição</title>
    </head>
    <body>

        <table style="text-align: left; width: 100%; " border="0">
            <tbody>
                <tr>
                    <td align="left"><h3>LO: <%=nome%></h3></td>
                    <td></td>
                    <td align="right"><a href="./Termos.jsp?idDisciplina=<%=idDisciplina%>&nome=<%=nome%>&idProfessor=<%=idProfessor%>">Voltar para LOs</a></td>
                </tr>
            </tbody>
        </table>

        <table style="text-align: left; width: 100%; " border="1">
            <thead>
                <tr>
                    <th><a href="./TermoConceito.jsp?idDisciplina=<%=idDisciplina%>&nome=<%=nome%>&idTermo=<%=idTermo%>&idProfessor=<%=idProfessor%>">Definição</a></th>
                    <th><a href="./TermoExemplos.jsp?idDisciplina=<%=idDisciplina%>&nome=<%=nome%>&idTermo=<%=idTermo%>&idProfessor=<%=idProfessor%>">Exemplos</a></th>
                    <th><a href="./TermoSinonimos.jsp?idDisciplina=<%=idDisciplina%>&nome=<%=nome%>&idTermo=<%=idTermo%>&idProfessor=<%=idProfessor%>">Sinônimos</a></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td colspan="4" rowspan="1">
                        <br>
                        <form action="CadConceito?cmd=incluir" method="POST">
                            Definição:
                            <input type="hidden" name="idProfessor" value="<%=idProfessor%>" />
                            <input type="hidden" name="idDisciplina" value="<%=idDisciplina%>" />
                            <input type="hidden" name="nome" value="<%=nome%>" />
                            <input type="hidden" name="idTermo" value="<%=idTermo%>" />
                            <% if (descricao.equals("")) {
                            %>
                            <h4><textarea name="descricao" rows="10" cols="100"><%=descricao%></textarea></h4>
                            <input type="submit" value="Cadastrar" />
                            <% } else {%>
                            <h4><%=descricao%></h4>
                            <% } //fim se %>
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>
        <h4>Os termos abaixo não foram cadastrados. Clique em cada um para cadastrar ou ignorar:</h4>
        <table border="1">
            <tbody>
                <%
                            TermoTempDAO daoTT = new TermoTempDAOImp();
                            List<TermoTemp> termotemps = daoTT.getListaFiltrada(idDisciplina);
                            for (int i = 0; i < termotemps.size(); i++) {
                %>
                <tr><td><%=termotemps.get(i).getNome()%></td>
                    <td><a href="./CadTermoTemp?idDisciplina=<%=idDisciplina%>&idTermo=<%=termotemps.get(i).getID()%>&nome=<%=termotemps.get(i).getNome()%>&idProfessor=<%=idProfessor%>">Cadastrar</a></td>
                    <td><form action="./CadTermoIgnorado?cmd=incluir" method="POST">
                            <input type="hidden" name="idProfessor" value="<%=idProfessor%>" />
                            <input type="hidden" name="idDisciplina" value="<%=idDisciplina%>" />
                            <input type="hidden" name="idTermo" value="<%=idTermo%>" />
                            <input type="hidden" name="nome" value="<%=nome%>" />
                            <input type="hidden" name="nomeTemp" value="<%=termotemps.get(i).getNome()%>" />
                            <input type="submit" value="Ignorar" />
                        </form></td>
                </tr>
                <% } //fim for%>
            </tbody>
        </table>
    </body>
</html>
