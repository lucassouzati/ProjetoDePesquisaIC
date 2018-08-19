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
        <link rel="stylesheet" type="text/css" href="../geral.css" />
    </head>
    <body>
        <div class="divCSSTableGenerator">
            <table style="text-align: left; width: 100%; " border="0">
                <tbody>
                    <tr>
                        <form action="CadTermo?cmd=editar" method="POST" >
                            <% if (descricao.equals("")) {
                            %>
                            <textarea name="novonome" value="<%=nome%>"><%=nome%></textarea>
                            <input type="hidden" name="idTermo" value="<%=idTermo%>" />
                            <input type="hidden" name="idProfessor" value="<%=idProfessor%>" />
                            <input type="hidden" name="idDisciplina" value="<%=idDisciplina%>" />
                            <input type="Submit" value="Atualizar nome">
                            <% } else {%>                        
                            <td align="left"><h3>LO: <%=nome%></h3></td>
                            <% } //fim se %>
                            <td></td>
                        </form>
                        <td align="right"><a href="./Termos.jsp?idDisciplina=<%=idDisciplina%>&nome=<%=nome%>&idProfessor=<%=idProfessor%>">Voltar para LOs</a></td>
                    </tr>
                </tbody>
            </table>
                    

            <table style="text-align: left; width: 100%; " border="0">
                <tr>
                        <td align="left"><h3>Definição</h3></td>
                        <td align="left"><a href="./TermoExemplos.jsp?idDisciplina=<%=idDisciplina%>&nome=<%=nome%>&idTermo=<%=idTermo%>&idProfessor=<%=idProfessor%>">Exemplos</a></td>
                        <td align="left"><a href="./TermoSinonimos.jsp?idDisciplina=<%=idDisciplina%>&nome=<%=nome%>&idTermo=<%=idTermo%>&idProfessor=<%=idProfessor%>">Sinônimos</a></td>
                </tr>
                
                    <tr>
                        
                        <td colspan="4" rowspan="1">
                            <br>
                            <div class="divCSSTableGenerator">
                                <form action="CadConceito?cmd=incluir" method="POST">

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
                            </div>
                        </td>
                        
                    </tr>
                
                
            </table>
                            
            <h4>Os termos abaixo não foram cadastrados. Clique em cada um para cadastrar ou ignorar:</h4>
            <table border="1">
                <tbody>
                   <tr> <form name="formtermoscomp" action ="./CadTermoComposto" method="post">
                    <%
                                TermoTempDAO daoTT = new TermoTempDAOImp();
                                List<TermoTemp> termotemps = daoTT.getListaFiltrada(idDisciplina);

                                for (int i = 0; i < termotemps.size(); i++) {
                    %>


                        <td><input type="checkbox" name="ckb" value=<%=termotemps.get(i).getID()%>/></td>
                        <input type="hidden" name="idProfessor" value="<%=idProfessor%>" />
                        <input type="hidden" name="idDisciplina" value="<%=idDisciplina%>" />

                        <td><%=termotemps.get(i).getNome()%></td>
                        <td><a href="./CadTermoTemp?idDisciplina=<%=idDisciplina%>&idTermo=<%=termotemps.get(i).getID()%>&nome=<%=termotemps.get(i).getNome()%>&idProfessor=<%=idProfessor%>">Cadastrar</a></td>
                        <td><a href="./CadTermoIgnorado?cmd=incluir&idProfessor=<%=idProfessor%>&idDisciplina=<%=idDisciplina%>&idTermo=<%=idTermo%>&nome=<%=nome%>&nomeTemp=<%=termotemps.get(i).getNome()%>">Ignorar

                            </a></td>
                    </tr>
                    <%
                                } //fim for%>



                    </tbody>

            </table>
            <input type="submit" value="Cadastrar Termo Composto"/>    
             </form>

        </div>
    </body>
</html>
