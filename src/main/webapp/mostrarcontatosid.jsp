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
                <p>${contatos.idContato}<br>
                   ${contatos.nome}<br>
                   ${contatos.endereco}<br>
                   ${contatos.telefone}<br>
                   ${contatos.email}</p><br>
            
            <p>Fundos bruto: ${contatos.fundos} <br>
               Líquido: ${liquido} <br>
               Taxas (15%): ${taxas} <br>
               Abate de contas ($240): ${contas} <br>
            </p>
            
            <button type="submit">Remover</button>
            </form>
            <form action="/ProjetoContatoSimples/alterarcontato.jsp">
                <button type="submit">Alterar</button>
            </form>
        
            <a href="/ProjetoContatoSimples/index.html">VOLTAR</a>
    </body>
</html>