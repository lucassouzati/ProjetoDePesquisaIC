<%-- 
    Document   : loginProfessor
    Created on : 27/10/2009, 23:51:20
    Author     : lefoly
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login Professor</title>
        <link rel="stylesheet" type="text/css" href="../login.css" />
    </head>
    <body background = "../Recursos/fundo7.jpg">
        <br>
        <div id="divmodulos" >
              
            
            
            <form action="CadProfessor?cmd=incluir" method="post">
                <div>
                    <h2>Nome:</h2><input type="text" name="nome" />

                    <h2>Login:</h2><input type="text" name="login" />

                    <h2>Senha:</h2><input type="password" name="senha" />
            
                    <input type="submit" name="btCadastrar" value="Enviar" />
                    </div>   
             </form>
        </div>       
    </body>
</html>

