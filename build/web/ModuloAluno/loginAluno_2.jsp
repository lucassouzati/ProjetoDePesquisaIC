<%-- 
    Document   : loginAluno
    Created on : 27/10/2009, 23:52:56
    Author     : lefoly
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login Aluno</title>
    </head>
    <body>
        <br>
        <table align="center">
            <tr>
                <td>
                    <h2 align="center">Novo Aluno</h2>
                    <br>
                    <form action="CadAluno?cmd=incluir" method="post">
                        <table>
                            <tr>
                                <td>Nome:</td><td><input type="text" name="nome" /></td>
                            </tr>
                            <tr>
                                <td>Login:</td><td><input type="text" name="login" /></td>
                            </tr>
                            <tr>
                                <td>Senha:</td><td><input type="password" name="senha" /></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" name="btCadastrar" value="Enviar" />
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
                <td>
                    <img src="../Recursos/branco.jpg" width="207" height="104" alt="branco"/>
                </td>
                <td>
                    <h2 align="center">Aluno Já Cadastrado</h2>
                    <br>
                    <br>
                    <form action="CadAluno?cmd=validar" method="post">
                        <table>
                            <tr>
                                <td>Login:</td><td><input type="text" name="login" /></td>
                            </tr>
                            <tr>
                                <td>Senha:</td><td><input type="password" name="senha" /></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" name="btLogar" value="Enviar" />
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>

