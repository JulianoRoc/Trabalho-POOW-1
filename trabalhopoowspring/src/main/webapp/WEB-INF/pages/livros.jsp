<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Cadastro de Livros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>
<body class="bg-dark text-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/dashbord">Dashbord</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Alternar navegação">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/funcionario">Funcionários</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/livro">Livros</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/cliente">Clientes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/emprestimo">Emprestimos</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h1 class="text-center mb-4">Cadastro de Livros</h1>

    <form:form action="${pageContext.request.contextPath}/livro" method="post" modelAttribute="livro" cssClass="bg-secondary p-4 rounded">
        <form:hidden path="id"/>

        <c:choose>
            <c:when test="${livro.id != 0}">
                <h2>Editar Livro</h2>
            </c:when>
            <c:otherwise>
                <h2>Adicionar Livro</h2>
            </c:otherwise>
        </c:choose>

        <div class="mb-3">
            <form:label path="titulo" cssClass="form-label">Título</form:label>
            <form:input path="titulo" cssClass="form-control" placeholder="Título" required="true"/>
        </div>

        <div class="mb-3">
            <form:label path="autor" cssClass="form-label">Autor</form:label>
            <form:input path="autor" cssClass="form-control" placeholder="Autor" required="true"/>
        </div>

        <div class="mb-3">
            <form:label path="anoPublicacao" cssClass="form-label">Ano de Publicação</form:label>
            <form:input path="anoPublicacao" cssClass="form-control" placeholder="Ano de Publicação" type="number" required="true"/>
        </div>

        <div class="mb-3">
            <form:label path="disponivel" cssClass="form-label">Disponível</form:label>
            <form:select path="disponivel" cssClass="form-select" required="true">
                <form:option value="true">Sim</form:option>
                <form:option value="false">Não</form:option>
            </form:select>
        </div>

        <c:choose>
            <c:when test="${livro.id != 0}">
                <button type="submit" class="btn btn-warning w-100">Alterar</button>
            </c:when>
            <c:otherwise>
                <button type="submit" class="btn btn-primary w-100">Cadastrar</button>
            </c:otherwise>
        </c:choose>
    </form:form>

    <c:if test="${not empty msg}">
        <div class="alert alert-${tipoMsg != null ? tipoMsg : 'danger'} mt-3" role="alert">
                ${msg}
        </div>
    </c:if>

    <h2 class="mt-5 text-center">Lista de Livros</h2>
    <table class="table table-dark table-striped mt-3">
        <thead>
        <tr>
            <th>Título</th>
            <th>Autor</th>
            <th>Ano</th>
            <th>Disponível</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="l" items="${livros}">
            <tr>
                <td>${l.titulo}</td>
                <td>${l.autor}</td>
                <td>${l.anoPublicacao}</td>
                <td>${l.disponivel ? 'Sim' : 'Não'}</td>
                <td>
                    <a href="livro/editar/${l.id}" class="btn btn-warning btn-sm">Editar</a>
                    <a href="livro/excluir/${l.id}" class="btn btn-danger btn-sm">Excluir</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</body>
</html>