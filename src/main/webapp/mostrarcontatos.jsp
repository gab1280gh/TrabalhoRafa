<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projeto Contatos Simples</title>
    </head>
    <body>
        <h1>Todos Contatos</h1>
        <c:forEach var="contato" items="${contatos}">
           
            <p>${contato.idContato}</p>
            <p>${contato.nome}</p>
            <p>${contato.endereco}</p>
            <p>${contato.telefone}</p>
            <p>${contato.email}</p>
            <p>${contato.fundos}</p><br>
            
        </c:forEach>
            
            
            <a href="/ProjetoContatoSimples/index.html">VOLTAR</a>
    </body>
</html>
