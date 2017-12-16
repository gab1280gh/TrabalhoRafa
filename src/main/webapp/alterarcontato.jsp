<%-- 
    Document   : alterarcontato
    Created on : Mar 23, 2017, 8:13:27 PM
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="/ProjetoContatoSimples/Contatos/Alterar" >
            <fieldset>
            Inserir nome: <input type="text" name="nome"/> 
            <br>
            <br>
            Inserir endereço: <input type="text" name="end"/> 
            <br>
            <br>
            Inserir telefone: <input type="text" name="tel"/> 
            <br>
            <br>
            Inserir email:  <input type="text" name="mail"/> 
            <br>
            <br>
            Inserir Fundos:  <input type="number" name="fundos"/> 
            <br>
            <br>
            <button type="submit">Alterar</button>
            </fieldset>
        </form>
    </body>
</html>
