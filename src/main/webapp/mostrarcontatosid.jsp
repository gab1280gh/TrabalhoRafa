<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projeto Contatos Simples</title>
    </head>
    <body>
        <h1>Busca Contato</h1>
        
             <form action="/ProjetoContatoSimples/Contatos/Remover">
            <p>${contatos.idContato}</p>
            <p>${contatos.nome}</p>
            <p>${contatos.endereco}</p>
            <p>${contatos.telefone}</p>
            <p>${contatos.email}</p><br>
            <button type="submit">Remover</button>
            </form>
            <form action="/ProjetoContatoSimples/alterarcontato.jsp">
                <button type="submit">Alterar</button>
            </form>
        
            <a href="/ProjetoContatoSimples/index.html">VOLTAR</a>
    </body>
</html>